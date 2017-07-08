package com.inva.hipstertest.service.mapper;

import com.inva.hipstertest.domain.*;
import com.inva.hipstertest.service.dto.CourseDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Course and its DTO CourseDTO.
 */
@Mapper(componentModel = "spring", uses = {FormMapper.class, LessonMapper.class, TeacherMapper.class, })
public interface CourseMapper {

    @Mapping(source = "form.id", target = "formId")
    @Mapping(source = "form.name", target = "formName")
    @Mapping(source = "lesson.id", target = "lessonId")
    @Mapping(source = "lesson.name", target = "lessonName")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.user.firstName", target = "teacherFirstName")
    @Mapping(source = "teacher.user.lastName", target = "teacherLastName")
    CourseDTO courseToCourseDTO(Course course);

    List<CourseDTO> coursesToCourseDTOs(List<Course> courses);

    @Mapping(source = "formId", target = "form")
    @Mapping(source = "lessonId", target = "lesson")
    @Mapping(source = "teacherId", target = "teacher")
    Course courseDTOToCourse(CourseDTO courseDTO);

    List<Course> courseDTOsToCourses(List<CourseDTO> courseDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */

    default Course courseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }

}
