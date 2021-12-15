package miu.edu.com.courseregistrationsystem.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
@Data
public class RegistrationRequestDTO {
    int student_id;
    int event_id;
    int group_id;
    //academic_block and request
    HashMap<Integer, List<RequestedCourseDTO>> request;
}
