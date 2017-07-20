package com.inva.hipstertest.freemarker.searchcriteria;

import com.inva.hipstertest.domain.enums.FilterType;

import java.time.ZonedDateTime;

/**
 * Created by slavkosoltys on 06.07.17.
 */
public class ScheduleSearchCriteria {

    private Long id;
    private ZonedDateTime date;
    private FilterType filterType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilterType getScheduleFilterType() {
        return filterType;
    }

    public void setScheduleFilterType(FilterType filterType) {
        this.filterType = filterType;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
}
