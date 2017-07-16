package com.inva.hipstertest.service;

import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.domain.enums.NotificationPeriod;
import com.inva.hipstertest.service.dto.NotificationDTO;

import java.util.List;

/**
 * Created by slavkosoltys on 16.07.17.
 */
public interface NotificationService {

    /**
     * Get all the notifications.
     *
     * @return the list of entities
     */
    List<NotificationDTO> findAll();

    /**
     * Get the "id" notification.
     *
     * @param id the id of the entity
     * @return the entity
     */
    NotificationDTO findOne(Long id);

    /**
     * Get all notifications by {@link User} for last week
     *
     * @param user {@link User} entity
     * @param period
     * @return list of notifications
     */
    List<NotificationDTO> findAllByUserForLastWeek(User user, NotificationPeriod period);
}
