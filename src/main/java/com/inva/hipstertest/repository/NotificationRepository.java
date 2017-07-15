package com.inva.hipstertest.repository;

import com.inva.hipstertest.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by slavkosoltys on 15.07.17.
 */
public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
