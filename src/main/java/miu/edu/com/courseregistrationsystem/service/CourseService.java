package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course getCourse(Integer id);
    Course save(Course course);
    List<Course> getAllCourses();
    void delete(Integer id);
}
