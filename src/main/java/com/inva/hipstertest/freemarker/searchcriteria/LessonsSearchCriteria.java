package com.inva.hipstertest.freemarker.searchcriteria;

import com.inva.hipstertest.domain.enums.LessonFilterType;

/**
 * Created by slavkosoltys on 10.07.17.
 */
public class LessonsSearchCriteria {

    private Long id;
    private LessonFilterType lessonFilterType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LessonFilterType getLessonFilterType() {
        return lessonFilterType;
    }

    public void setLessonFilterType(LessonFilterType lessonFilterType) {
        this.lessonFilterType = lessonFilterType;
    }
}
