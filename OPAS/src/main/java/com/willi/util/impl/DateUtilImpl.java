package com.willi.util.impl;

import com.willi.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Calendar;

public class DateUtilImpl implements DateUtil {

    Calendar calendar = Calendar.getInstance();

    @Override
    public String getDate() {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONDAY) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return String.valueOf(year) + "/" + String.valueOf(month) + "/" + String.valueOf(day)
                + " " + String.valueOf(hour) + ":" + String.valueOf(minute) + ":" + String.valueOf(second);
    }
}
