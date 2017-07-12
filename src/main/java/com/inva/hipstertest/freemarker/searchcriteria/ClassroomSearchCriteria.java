package com.inva.hipstertest.freemarker.searchcriteria;

/**
 * Created by slavkosoltys on 12.07.17.
 */
public class ClassroomSearchCriteria {
    
    private Integer lessonPosition;
    private String date;

    public Integer getLessonPosition() {
        return lessonPosition;
    }

    public void setLessonPosition(Integer lessonPosition) {
        this.lessonPosition = lessonPosition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
