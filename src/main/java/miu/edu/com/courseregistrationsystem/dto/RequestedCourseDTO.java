package miu.edu.com.courseregistrationsystem.dto;

import lombok.Data;

@Data
public class RequestedCourseDTO {
    int course_id;
    int priority;
    boolean requested;
}
