package com.inva.hipstertest.service.dto;


import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the Attendances entity.
 */
public class PupilAttendanceDTO implements Serializable {

    private Long pupilId;

    private Long lessonId;

    private Integer grade;

    private ZonedDateTime date;

    public Long getPupilId() {
        return pupilId;
    }

    public void setPupilId(Long pupilId) {
        this.pupilId = pupilId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PupilAttendanceDTO that = (PupilAttendanceDTO) o;
        return Objects.equals(pupilId, that.pupilId) &&
            Objects.equals(lessonId, that.lessonId) &&
            Objects.equals(grade, that.grade) &&
            Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pupilId, lessonId, grade, date);
    }

    @Override
    public String toString() {
        return "PupilAttendanceDTO{" +
            "pupilId=" + pupilId +
            ", lessonId=" + lessonId +
            ", grade=" + grade +
            ", date=" + date +
            '}';
    }
}
