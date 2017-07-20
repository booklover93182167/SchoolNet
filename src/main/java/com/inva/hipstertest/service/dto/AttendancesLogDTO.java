package com.inva.hipstertest.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the AttendancesLog entity.
 */
public class AttendancesLogDTO implements Serializable {

    private Long id;

    @NotNull
    private ZonedDateTime date;

    private Integer oldGrade;

    private Integer newGrade;

    @NotNull
    private String reason;

    private Long teacherId;

    private Long attendancesId;

    private String teacherFirstName;

    private String teacherLastName;

    private String pupilFirstName;

    private String pupilLastName;

    private String lessonName;

    private String formName;

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
    public Integer getOldGrade() {
        return oldGrade;
    }

    public void setOldGrade(Integer oldGrade) {
        this.oldGrade = oldGrade;
    }
    public Integer getNewGrade() {
        return newGrade;
    }

    public void setNewGrade(Integer newGrade) {
        this.newGrade = newGrade;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getAttendancesId() {
        return attendancesId;
    }

    public void setAttendancesId(Long attendancesId) {
        this.attendancesId = attendancesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AttendancesLogDTO attendancesLogDTO = (AttendancesLogDTO) o;

        if ( ! Objects.equals(id, attendancesLogDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "AttendancesLogDTO{" +
            "id=" + id +
            ", date='" + date + "'" +
            ", oldGrade='" + oldGrade + "'" +
            ", newGrade='" + newGrade + "'" +
            ", reason='" + reason + "'" +
            '}';
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

    public String getPupilFirstName() {
        return pupilFirstName;
    }

    public void setPupilFirstName(String pupilFirstName) {
        this.pupilFirstName = pupilFirstName;
    }

    public String getPupilLastName() {
        return pupilLastName;
    }

    public void setPupilLastName(String pupilLastName) {
        this.pupilLastName = pupilLastName;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
