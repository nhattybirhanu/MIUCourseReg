package miu.edu.com.courseregistrationsystem.dto;

import lombok.Data;

@Data
public class CourseOfferingDto {
    int faculty_id;
    int block_id;
    int course_id;
    int totalSeat;
}
