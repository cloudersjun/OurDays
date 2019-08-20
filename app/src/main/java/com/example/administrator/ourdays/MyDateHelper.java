package com.example.administrator.ourdays;


//import android.util.Log

import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.util.Date;


/**
 * Created by Administrator on 2016/9/14.
 */
public class MyDateHelper {
    /**
     * calculate how many years,months,days... between the two date
     *
     * @param begin
     * @param end
     * @return
     */
    public static String getDateDiff(Date begin, Date... end) {
        Interval interval = new Interval(begin.getTime(), end.length > 0 ? end[0].getTime() : new Date().getTime());

        Period p = interval.toPeriod(PeriodType.yearMonthDayTime());
        String yearStr = p.getYears() + "年";
        String monthStr = p.getMonths() + "个月";
        String dayStr = p.getDays() + "天";
        String hourStr = p.getHours() + "小时";
        String minuteStr = p.getMinutes() + "分";
        String secondStr = p.getSeconds() + "秒";

        return yearStr + monthStr + dayStr + hourStr + minuteStr + secondStr;
    }

    public static double getYearDiff(Date begin,Date end){
        Interval interval = new Interval(begin.getTime(), end.getTime());

        Period p = interval.toPeriod(PeriodType.yearMonthDayTime());
        return p.getYears()+p.getMonths()/12.0;
    }


}
