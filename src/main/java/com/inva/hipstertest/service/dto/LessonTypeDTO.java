package com.inva.hipstertest.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the LessonType entity.
 */
public class LessonTypeDTO implements Serializable {

    private Long id;

    private Boolean enabled;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LessonTypeDTO lessonTypeDTO = (LessonTypeDTO) o;

        return Objects.equals(id, lessonTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "LessonTypeDTO{" +
            "id=" + id +
            ", enabled='" + enabled + "'" +
            ", name='" + name + "'" +
            '}';
    }
}
