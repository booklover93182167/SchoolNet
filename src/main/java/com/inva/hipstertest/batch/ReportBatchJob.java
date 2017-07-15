package com.inva.hipstertest.batch;

import com.inva.hipstertest.domain.*;
import com.inva.hipstertest.domain.enums.NotificationType;
import com.inva.hipstertest.domain.enums.SampleView;
import com.inva.hipstertest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by slavkosoltys on 14.07.17.
 */
@Component
public class ReportBatchJob {

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private AttendancesRepository attendancesRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Scheduled(cron = "0,10 * * * * *")
    public void run() {
        List<Form> forms = formRepository.findAll();
        for (Form form : forms) {
            List<Lesson> lessons = lessonRepository.getDistinctLessonsByFormId(form.getId());
            List<Pupil> pupils = pupilRepository.findAllByFormId(form.getId());
            for (Pupil pupil : pupils) {
                analyzePupilGradesByLessons(pupil, lessons, SampleView.QUARTER, 10);
            }
        }
    }

    private void analyzePupilGradesByLessons(Pupil pupil, List<Lesson> lessons, SampleView view, Integer precinct) {
        StringBuilder message = new StringBuilder("");
        ZonedDateTime endDate = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime startDate;

        for (Lesson lesson : lessons) {
            int countGrades = 0;
            int sum = 0;
            List<Attendances> attendances;
            switch (view) {
                case WEEK:
                    startDate = endDate.minusWeeks(1);
                    attendances = attendancesRepository.findAllByPupilIdAndLessonIdAndDateBetween(pupil.getId(), lesson.getId(), startDate, endDate);
                    break;
                case MONTH:
                    startDate = endDate.minusMonths(1);
                    attendances = attendancesRepository.findAllByPupilIdAndLessonIdAndDateBetween(pupil.getId(), lesson.getId(), startDate, endDate);
                    break;
                case QUARTER:
                    startDate = endDate.minusMonths(3);
                    attendances = attendancesRepository.findAllByPupilIdAndLessonIdAndDateBetween(pupil.getId(), lesson.getId(), startDate, endDate);
                    break;
                default:
                    startDate = endDate.minusMonths(1);
                    attendances = attendancesRepository.findAllByPupilIdAndLessonIdAndDateBetween(pupil.getId(), lesson.getId(), startDate, endDate);
                    break;
            }
            for (Attendances grade : attendances) {
                if (grade.getGrade() != 0) {
                    sum += grade.getGrade();
                    countGrades++;
                }
            }
            if (countGrades > 0) {
                if (sum / countGrades < precinct) {
                    message.append(pupil.getUser().getFirstName()).append(" ").append(pupil.getUser().getLastName())
                        .append(" from - ").append(pupil.getForm().getName()).append(" from lesson: - ")
                        .append(lesson.getName()).append(" have average rating: ").append((double) sum / countGrades);
                    informUsers(pupil, message);
                    message.setLength(0);
                }
            }
        }
    }

    private void informUsers(Pupil pupil, StringBuilder message) {
        List<Notification> notifications = new ArrayList<>();
        List<Parent> parents = parentRepository.findAllByPupilId(pupil.getId());
        if (parents != null) {
            for (Parent parent : parents) {
                notifications.add(Notification.builder().user(parent.getUser()).message(message.toString()).type(NotificationType.GRADE).build());
            }
        }
        User teacher = pupil.getForm().getTeacher().getUser();
        notifications.add(Notification.builder().user(teacher).message(message.toString()).type(NotificationType.GRADE).build());
        notificationRepository.save(notifications);
    }
}
