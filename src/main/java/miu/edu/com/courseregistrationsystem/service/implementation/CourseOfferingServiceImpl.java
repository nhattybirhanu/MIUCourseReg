package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.service.CourseOfferingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Override
    public void request(Student student, int priority) {

    }

    @Override
    public void update(CourseOffering courseOffering) {

    }
}
