package com.light.myilists.utils;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by light on 15/6/24.
 */
public class DateUtils {

    public static String getDate(long time) {

        if(time < 0){
            time = System.currentTimeMillis();
        }

        String date = "";

        Calendar mCalendar=Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int mHour = mCalendar.get(Calendar.HOUR);
        int mMinuts = mCalendar.get(Calendar.MINUTE);
        int mon = mCalendar.get(Calendar.MONTH)+1;
        int day = mCalendar.get(Calendar.DATE);

        date = day + " / " + mon;
        return  date;
    }
}
