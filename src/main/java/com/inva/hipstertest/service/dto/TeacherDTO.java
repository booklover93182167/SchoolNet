package com.inva.hipstertest.service.dto;

import com.inva.hipstertest.domain.Lesson;
import com.inva.hipstertest.service.dto.FormDTO;
import com.inva.hipstertest.service.dto.LessonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class TeacherDTO implements Serializable {

    private Long id;

    @NotNull
    private Boolean enabled;

    private Long userId;

    private Long formId;

    private String formName;
    private FormDTO form;   // what TODO: remove this field and then correct builder

    private Set<LessonDTO> lessons = new HashSet<>(); // the hell

    private Set<String> lessonsName = new HashSet<>(); // is this?

    private Long schoolId;

    private String lastName;

    private String firstName;

    private String email;

    private String login;

    public TeacherDTO() {
    }

    public TeacherDTO(Builder builder) {
        this.id = builder.id;
        this.enabled = builder.enabled;
        this.userId = builder.userId;
        this.formId = builder.formId;
        this.formName = builder.formName;
        this.form = builder.form;
        this.lessons = builder.lessons;
        this.lessonsName = builder.lessonsName;
        this.schoolId = builder.schoolId;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.email = builder.email;
        this.login = builder.login;
    }

    public FormDTO getForm() {
        return form;
    }

    public void setForm(FormDTO form) {
        this.form = form;
    }

    public Set<String> getLessonsName() {
        return lessonsName;
    }

    public void setLessonsName(Set<String> lessonsName) {
        this.lessonsName = lessonsName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
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

    public Set<LessonDTO> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonDTO> lessons) {
        this.lessons = lessons;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeacherDTO teacherDTO = (TeacherDTO) o;

        if (!Objects.equals(id, teacherDTO.id)) {
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
        return "TeacherDTO{" +
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
        private FormDTO form;
        private Set<LessonDTO> lessons = new HashSet<>();
        private Set<String> lessonsName = new HashSet<>();
        private Long schoolId;
        private String lastName;
        private String firstName;
        private String email;
        private String login;

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

        public Builder form(FormDTO form) {
            this.form = form;
            return this;
        }

        public Builder lessons(Set<LessonDTO> lessons) {
            this.lessons = lessons;
            return this;
        }

        public Builder lessonsName(Set<String> lessonsName) {
            this.lessonsName = lessonsName;
            return this;
        }

        public Builder schoolId(Long schoolId) {
            this.schoolId = schoolId;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder login(String login) {
            this.login = login;
            return this;
        }

        public TeacherDTO build() {
            return new TeacherDTO(this);
        }
    }
}
