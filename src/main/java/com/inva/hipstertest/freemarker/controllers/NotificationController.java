package com.inva.hipstertest.freemarker.controllers;

import com.inva.hipstertest.domain.enums.NotificationPeriod;
import com.inva.hipstertest.service.NotificationService;
import com.inva.hipstertest.service.UserService;
import com.inva.hipstertest.service.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NotificationController {

    private final Logger log = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    /**
     * Get all notifications by current user.
     *
     * @param period period of notifications.
     * @return list of notification DTOs
     */
    @RequestMapping(value = "freemarker/notifications", method = RequestMethod.GET)
    public @ResponseBody
    List<NotificationDTO> getAllNotificationByCurrentUser(@RequestParam NotificationPeriod period) {
        log.debug("Request to get all notification for current user");
        return notificationService.findAllByUserForLastWeek(userService.findByLoginUserId(), period);
    }
}
