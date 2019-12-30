package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    //String 转化为 Date
    public static Date getDate(String date) throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    //2019-12-22 19:53:00
        return format.parse(date);
    }

    //date 转化为 String
    public static String getFormatDate(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
