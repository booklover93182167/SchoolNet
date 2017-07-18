package com.inva.hipstertest.service.dto;

import com.inva.hipstertest.domain.enums.NotificationType;

import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the Notification entity.
 */
public class NotificationDTO {

    private Long id;
    private NotificationType type;
    private String message;
    private ZonedDateTime date;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDTO that = (NotificationDTO) o;
        return Objects.equals(id, that.id) &&
            type == that.type &&
            Objects.equals(message, that.message) &&
            Objects.equals(date, that.date) &&
            Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, message, date, userId);
    }
}
