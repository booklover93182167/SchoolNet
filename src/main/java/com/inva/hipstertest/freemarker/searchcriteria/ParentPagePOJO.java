package com.inva.hipstertest.freemarker.searchcriteria;

import java.time.ZonedDateTime;

public class ParentPagePOJO {

    private Long pupilId;
    private Long pupilFormId;
    private Long lessonId;
    private ZonedDateTime date;

    public Long getPupilId() {
        return pupilId;
    }

    public void setPupilId(Long pupilId) {
        this.pupilId = pupilId;
    }

    public Long getPupilFormId() {
        return pupilFormId;
    }

    public void setPupilFormId(Long pupilFormId) {
        this.pupilFormId = pupilFormId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessongId) {
        this.lessonId = lessongId;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

}
