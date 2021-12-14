package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseOfferingService {
    void update(CourseOffering courseOffering);
    CourseOffering create(CourseOffering courseOffering);
    CourseOffering findById(int id);
    Page<CourseOffering> findAll(Pageable pageable);
    void delete(int id);
}
