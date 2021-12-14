package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.domain.Student;

public interface CourseOfferingService {
    void request(Student student, int priority);
    void update(CourseOffering courseOffering);
}
