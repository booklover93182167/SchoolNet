package com.inva.hipstertest.freemarker.searchcriteria;

import com.inva.hipstertest.domain.enums.ScheduleType;

/**
 * Created by slavkosoltys on 06.07.17.
 */
public class ScheduleSearchCriteria {

    private Long id;
    private String date;
    private ScheduleType scheduleType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
