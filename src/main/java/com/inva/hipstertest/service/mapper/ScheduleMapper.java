package com.inva.hipstertest.service.mapper;

import com.inva.hipstertest.domain.*;
import com.inva.hipstertest.service.dto.ScheduleDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Schedule and its DTO ScheduleDTO.
 */
@Mapper(componentModel = "spring", uses = {LessonMapper.class, FormMapper.class, ClassroomMapper.class, TeacherMapper.class, CourseMapper.class, LessonTypeMapper.class, })
public interface ScheduleMapper {

    @Mapping(source = "classroom.id", target = "classroomId")
    @Mapping(source = "classroom.name", target = "classroomName")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "course.form.id", target = "formId")
    @Mapping(source = "course.form.name", target = "formName")
    @Mapping(source = "course.lesson.id", target = "lessonId")
    @Mapping(source = "course.lesson.name", target = "lessonName")
    @Mapping(source = "course.teacher.id", target = "teacherId")
    @Mapping(source = "course.teacher.user.firstName", target = "teacherFirstName")
    @Mapping(source = "course.teacher.user.lastName", target = "teacherLastName")
    @Mapping(source = "teacher.user.firstName", target = "tempTeacherFirstName")
    @Mapping(source = "teacher.user.lastName", target = "tempTeacherLastName")
    @Mapping(source = "lessonType.id", target = "lessonTypeId")
    @Mapping(source = "lessonType.name", target = "lessonTypeName")
    ScheduleDTO scheduleToScheduleDTO(Schedule schedule);

    List<ScheduleDTO> schedulesToScheduleDTOs(List<Schedule> schedules);

    @Mapping(target = "attendances", ignore = true)
    @Mapping(source = "classroomId", target = "classroom")
    @Mapping(source = "courseId", target = "course")
    @Mapping(source = "lessonId", target = "course.lesson")
    @Mapping(source = "formId", target = "course.form")
    @Mapping(source = "teacherId", target = "course.teacher")
    @Mapping(source = "lessonTypeId", target = "lessonType")
    Schedule scheduleDTOToSchedule(ScheduleDTO scheduleDTO);

    List<Schedule> scheduleDTOsToSchedules(List<ScheduleDTO> scheduleDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */

    default Schedule scheduleFromId(Long id) {
        if (id == null) {
            return null;
        }
        Schedule schedule = new Schedule();
        schedule.setId(id);
        return schedule;
    }

}
