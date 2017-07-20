package com.inva.hipstertest.freemarker.searchcriteria;

import com.inva.hipstertest.domain.enums.FilterType;

/**
 * Created by slavkosoltys on 10.07.17.
 */
public class LessonsSearchCriteria {

    private Long id;
    private FilterType filterType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilterType getFilterType() {
        return filterType;
    }

    public void setFilterType(FilterType filterType) {
        this.filterType = filterType;
    }
}
