package com.inva.hipstertest.service.mapper;

import com.inva.hipstertest.domain.Notification;
import com.inva.hipstertest.service.dto.NotificationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper between {@link Notification} and {@link NotificationDTO}.
 */
@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source = "user.id", target = "userId")
    NotificationDTO map(Notification notification);

    List<NotificationDTO> map(List<Notification> notifications);
}
