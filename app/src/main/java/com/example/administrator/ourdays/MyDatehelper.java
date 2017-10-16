package com.example.administrator.ourdays;


import android.util.Log;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Date;


/**
 * Created by Administrator on 2016/9/14.
 */
public class MyDatehelper {
    /**
     * calculate how many years,months,days... between the two date
     *
     * @param begin
     * @param end
     * @return
     */
    public static String getDateDiff(Date begin, Date... end) {
        StringBuffer sb = new StringBuffer();
        Interval interval = new Interval(begin.getTime(), end.length>0?end[0].getTime():new Date().getTime());
        long year;
        long month;
        long day;
        long hour;
        long minute;
        long second;
//        Period p=interval.toPeriod();
        Period p=interval.toPeriod(PeriodType.yearMonthDayTime());
        day = p.getDays();
        year = p.getYears();
        month = p.getMonths();
        hour = p.getHours();
        minute = p.getMinutes();
        second = p.getSeconds();
        Log.i("year",year+"");
        Log.i("month",month+"");
        Log.i("day",day+"");
        Log.i("hour",hour+"");
        Log.i("minute",minute+"");
        Log.i("second",second+"");
        String yearStr = year+"年";
        String monthStr = month+"个月";
        String dayStr = day+"天";
        String hourStr = hour+"小时";
        String minuteStr = minute+"分";
        String secondStr = second+"秒";
        //        String yearStr = year > 0 ? (year > 1 ? year + " years " : " a year ") : "";
        //        String monthStr = month > 0 ? (month > 1 ? month + " months " : " a month ") : "";
//        String dayStr = day > 0 ? (day > 1 ? day + " days " : "a day") : "";
//        String dayStr = day > 0 ? (day > 1 ? day + " days " : "a day") : "";
//        String hourStr = hour > 0 ? (hour > 1 ? hour + " hours " : " an hour ") : "";
//        String minuteStr = minute > 0 ? (minute > 1 ? minute + " minutes " : " a minute ") : "";
//        String secondStr = second > 0 ? (second > 1 ? second + " seconds " : " a second ") : "";
        sb.append(yearStr).append(monthStr).append(dayStr).append(hourStr).append(minuteStr).append(secondStr);
        return sb.toString();
    }
}
