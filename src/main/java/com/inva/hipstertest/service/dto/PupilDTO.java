package com.inva.hipstertest.service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the Pupil entity.
 */
public class PupilDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean enabled;

    private Long userId;

    private Long formId;

    private String formName;

    private Set<ParentDTO> parents;

    private String firstName;

    private String lastName;

    public PupilDTO() {
    }

    private PupilDTO(Builder builder) {
        this.id = builder.id;
        this.enabled = builder.enabled;
        this.userId = builder.userId;
        this.formId = builder.formId;
        this.formName = builder.formName;
        this.parents = builder.parents;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String pupilFirstName) {
        this.firstName = pupilFirstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String pupilLastName) {
        this.lastName = pupilLastName;
    }

    public Set<ParentDTO> getParents() {
        return parents;
    }

    public void setParents(Set<ParentDTO> parents) {
        this.parents = parents;
    }

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PupilDTO pupilDTO = (PupilDTO) o;

        if (!Objects.equals(id, pupilDTO.id)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PupilDTO{" +
            "id=" + id +
            ", enabled='" + enabled + "'" +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;
        private Boolean enabled;
        private Long userId;
        private Long formId;
        private String formName;
        private Set<ParentDTO> parents;
        private String firstName;
        private String lastName;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder formId(Long formId) {
            this.formId = formId;
            return this;
        }

        public Builder formName(String formName) {
            this.formName = formName;
            return this;
        }

        public Builder parents(Set<ParentDTO> parents) {
            this.parents = parents;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PupilDTO build() {
            return new PupilDTO(this);
        }
    }
}
