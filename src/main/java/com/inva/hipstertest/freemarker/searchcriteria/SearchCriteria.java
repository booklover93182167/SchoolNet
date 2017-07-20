package com.inva.hipstertest.freemarker.searchcriteria;

import java.time.ZonedDateTime;

/**
 * Created by slavkosoltys on 12.07.17.
 */
public class SearchCriteria {

    private Long id;
    private Integer lessonPosition;
    private ZonedDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLessonPosition() {
        return lessonPosition;
    }

    public void setLessonPosition(Integer lessonPosition) {
        this.lessonPosition = lessonPosition;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }


}
