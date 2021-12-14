package miu.edu.com.courseregistrationsystem.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class AcademicBlockDto {
    private String name;
    private String semester;
    private  int startWeek;
    private  int endWeek;
    private int year;
    private int month;
    private int day;






}
