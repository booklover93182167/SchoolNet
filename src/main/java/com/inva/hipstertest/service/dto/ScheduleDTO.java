package com.inva.hipstertest.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Schedule entity.
 */
public class ScheduleDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime date;

    private String homework;

    @NotNull
    private Integer lessonPosition;

    @NotNull
    private Boolean enabled;

    private Long lessonId;

    private Long formId;

    private Long classroomId;

    private String classroomName;

    private Long teacherId;

    private String tempTeacherFirstName;

    private String tempTeacherLastName;

    private Long courseId;

    private String formName;

    private String lessonName;

    private Long tempTeacherId;

    private String teacherFirstName;

    private String teacherLastName;

    private Long lessonTypeId;

    private String lessonTypeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public Integer getLessonPosition() {
        return lessonPosition;
    }

    public void setLessonPosition(Integer lessonPosition) {
        this.lessonPosition = lessonPosition;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(Long lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScheduleDTO scheduleDTO = (ScheduleDTO) o;
        if(scheduleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), scheduleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", homework='" + getHomework() + "'" +
            ", lessonPosition='" + getLessonPosition() + "'" +
            ", enabled='" + getEnabled() + "'" +
            ", course='" + getCourseId() + "'" +
            ", form='" + getFormName() + "'" +
            ", lesson='" + getLessonName() + "'" +
            ", teacher='" + getTeacherFirstName() + " " + getTeacherLastName() + "'" +
            ", temp_teacher='" + getTempTeacherFirstName() + " " + getTempTeacherLastName() + "'" +
            "}";
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public String getTempTeacherFirstName() {
        return tempTeacherFirstName;
    }

    public void setTempTeacherFirstName(String tempTeacherFirstName) {
        this.tempTeacherFirstName = tempTeacherFirstName;
    }

    public String getTempTeacherLastName() {
        return tempTeacherLastName;
    }

    public void setTempTeacherLastName(String tempTeacherLastName) {
        this.tempTeacherLastName = tempTeacherLastName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Long getTempTeacherId() {
        return tempTeacherId;
    }

    public void setTempTeacherId(Long tempTeacherId) {
        this.tempTeacherId = tempTeacherId;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getLessonTypeName() {
        return lessonTypeName;
    }

    public void setLessonTypeName(String lessonTypeName) {
        this.lessonTypeName = lessonTypeName;
    }

}
