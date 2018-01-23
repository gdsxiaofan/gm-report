package com.huaxia.tongyong.util.date;

import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by gongdaoshun on 2017/10/15.
 * 时间格式转换工具类
 */
@Slf4j
public class DateUtil {
    public static final String LONGFMT1="yyyy-MM-dd HH:mm:ss";
    public static final String SHORTFMT1="yyyy-MM-dd";

    /**
     * 格式化时间,默认格式为：yyyy-MM-dd HH:mm:ss
     * @param date
     * @param dateFmt
     * @return
     */
    public static String dateToString(Date date,String dateFmt){
        if(StringUtils.isBlank(dateFmt)){
            dateFmt = LONGFMT1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFmt);
        String returnStr = sdf.format(date);

        return returnStr;
    }

    /**
     * 固定格式字符串转时间
     * @param dateStr
     * @param dateFmt
     * @return
     */
    public static Date stringToDate(String dateStr,String dateFmt){
        Assert.isTrue(StringUtils.isNotBlank(dateStr),"请输入对应的时间");
        Assert.isTrue(StringUtils.isNotBlank(dateFmt),"需指定对应的时间格式");

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(dateFmt);
            Date returnDate = sdf.parse(dateStr);
            return returnDate;
        }catch (Exception e){
            log.error("时间转换出错：",e);
        }
        return null;
    }

    /**
     * 获取某年某月最后一天
     * @param reportYear
     * @param reportMonth
     * @return
     */
    public static String getLastDayByMonth(int reportYear, int reportMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, reportYear);
        cal.set(Calendar.MONTH, reportMonth - 1);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        return String.valueOf(maxDate);
    }
}
