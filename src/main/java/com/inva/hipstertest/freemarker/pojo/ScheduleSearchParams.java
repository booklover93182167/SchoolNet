package com.inva.hipstertest.freemarker.pojo;

import java.time.ZonedDateTime;

public class ScheduleSearchParams {

    private Long pupilFormId;
    private ZonedDateTime date;

    public Long getPupilFormId() {
        return pupilFormId;
    }

    public void setPupilFormId(Long pupilFormId) {
        this.pupilFormId = pupilFormId;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
