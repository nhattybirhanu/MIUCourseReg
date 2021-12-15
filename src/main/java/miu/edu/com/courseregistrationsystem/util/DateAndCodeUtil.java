package miu.edu.com.courseregistrationsystem.util;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndCodeUtil {


    public static LocalDateTime[] getStartAndEndTime(int year , int month, int day, int startWeek, int end_week) {
        LocalDateTime start=LocalDateTime.of(year,month,day,0,0);

        LocalDateTime end=start.plusWeeks(end_week-startWeek+1);
        return new LocalDateTime[]{start,end};
    }
    public static String code(LocalDateTime start,int startWeek,int endWeek){

        return start.getYear()+"-"+start.getMonthValue()+""+weekCode(startWeek)+"-"+
                start.getMonthValue()+""+weekCode(endWeek);
    }
    public static String weekCode(int week){
        switch (week){
            case 1:return "A";
            case 2:return "B";
            case 3:return "C";
            case 4:return "D";

        }
        return "null";
    }
    public static String courseOffering(String course_code,String ac_code,String faculty_code){

        return course_code+"-"+ac_code+"-"+faculty_code;
    }
//    public static LocalDateTime convertToDate(String date){
//        String pattern = "MM-dd-yyyy";
//        DateTimeFormatter simpleDateFormat = DateTimeFormatter.ofPattern(pattern);
//        LocalDateTime dateTime= null;
//        try {
//            dateTime = LocalDateTime.parse(date,simpleDateFormat);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        return dateTime;
//    }
}
