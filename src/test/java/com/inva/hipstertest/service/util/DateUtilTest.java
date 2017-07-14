package com.inva.hipstertest.service.util;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by slavkosoltys on 28.06.17.
 */
public class DateUtilTest {
    private final Map<String, ZonedDateTime> stringZonedDateTime = new HashMap<String, ZonedDateTime>();

    @Before
    public void setUp() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2017, Month.JUNE, 30, 17, 23, 55);
        stringZonedDateTime.put("2017-06-30T17:23:55", localDateTime.atZone(ZoneId.systemDefault()));
        localDateTime = LocalDateTime.of(2016, Month.SEPTEMBER, 1, 20, 6, 11);
        stringZonedDateTime.put("2016-09-01T20:06:11", localDateTime.atZone(ZoneId.systemDefault()));
    }

    @Test
    public void getZonedDateTime() throws Exception {
        for (Map.Entry<String, ZonedDateTime> entry :
            stringZonedDateTime.entrySet()) {
            ZonedDateTime zonedDateTimeFromString = DateUtil.getZonedDateTime(entry.getKey());
            assertEquals(entry.getValue(), zonedDateTimeFromString);
        }
    }
}
