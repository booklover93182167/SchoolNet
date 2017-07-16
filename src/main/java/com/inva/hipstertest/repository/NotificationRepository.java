package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by slavkosoltys on 15.07.17.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserIdAndDateBetweenOrderByDate(Long userId, ZonedDateTime startDate, ZonedDateTime endDate);

}
