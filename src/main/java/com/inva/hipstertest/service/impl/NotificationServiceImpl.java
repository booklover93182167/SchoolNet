package com.inva.hipstertest.service.impl;

import com.inva.hipstertest.domain.User;
import com.inva.hipstertest.domain.enums.NotificationPeriod;
import com.inva.hipstertest.repository.NotificationRepository;
import com.inva.hipstertest.service.NotificationService;
import com.inva.hipstertest.service.dto.NotificationDTO;
import com.inva.hipstertest.service.mapper.NotificationMapper;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Service Implementation for managing Notification
 */
@Service
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

    private final Logger log = LoggerFactory.getLogger(LessonServiceImpl.class);

    @Autowired
    private final NotificationRepository notificationRepository;

    @Autowired
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public List<NotificationDTO> findAll() {
        log.debug("Request to get all notifications");
        return notificationMapper.map(notificationRepository.findAll());
    }

    @Override
    public NotificationDTO findOne(Long id) {
        log.debug("Request to get notification by id {}", id);
        return notificationMapper.map(notificationRepository.findOne(id));
    }

    @Override
    public List<NotificationDTO> findAllByUserForLastWeek(User user, NotificationPeriod period) {
        Validate.notNull(user);
        log.debug("Request to get notifications for last week by user id {}", user.getId());
        ZonedDateTime endDate = ZonedDateTime.now().truncatedTo(ChronoUnit.DAYS);
        ZonedDateTime startDate = getStartDate(period, endDate);
        return notificationMapper.map(notificationRepository.findAllByUserIdAndDateBetweenOrderByDate(user.getId(), startDate, endDate));
    }

    private ZonedDateTime getStartDate(NotificationPeriod period, ZonedDateTime today) {
        switch (period) {
            case DAY:
                return today.minusDays(1);
            case WEEK:
                return today.minusWeeks(1);
            case MONTH:
                return today.minusMonths(1);
            case YEAR:
                return today.minusYears(1);
            default:
                throw new IllegalArgumentException("invalid notification period type");
        }
    }
}
