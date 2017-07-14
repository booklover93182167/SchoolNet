package com.inva.hipstertest.batch;

import com.inva.hipstertest.domain.Attendances;
import com.inva.hipstertest.domain.Form;
import com.inva.hipstertest.domain.Lesson;
import com.inva.hipstertest.domain.Pupil;
import com.inva.hipstertest.repository.AttendancesRepository;
import com.inva.hipstertest.repository.FormRepository;
import com.inva.hipstertest.repository.LessonRepository;
import com.inva.hipstertest.repository.PupilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by slavkosoltys on 14.07.17.
 */
@Component
public class ReportBatchJob {

    @Autowired
    private PupilRepository pupilRepository;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private AttendancesRepository attendancesRepository;

    @Scheduled(cron = "0,10 * * * * *")
    public void run() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>> JOB <<<<<<<<<<<<<<<<<<<<<<");
        List<Form> forms = formRepository.findAll();
        for (Form form : forms) {
            List<Lesson> lessons = lessonRepository.getDistinctLessonsByFormId(form.getId());
            List<Pupil> pupils = pupilRepository.findAllByFormId(form.getId());
            for (Pupil pupil : pupils) {
                analyzePupilGradesByLessons(pupil, lessons);
            }

        }
    }

    private void analyzePupilGradesByLessons(Pupil pupil, List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            int countGrades = 0;
            int sum = 0;
            List<Attendances> attendances = attendancesRepository.findAllByPupilIdAndLessonId(pupil.getId(), lesson.getId());
            for (Attendances grade : attendances) {
                if (grade.getGrade() != 0) {
                    sum += grade.getGrade();
                    countGrades++;
                }
            }
            if (countGrades > 0) {
                if (sum / countGrades < 7) {
                    System.out.println("!!!!!!!!!" + pupil.getUser().getFirstName() + " FORM NAME - " +
                        pupil.getForm().getName() + " from LESSON -" + lesson.getName() + "have average rating: " +
                        sum / countGrades + "!!!!!!!!!");
                }
            }
        }
    }
}
