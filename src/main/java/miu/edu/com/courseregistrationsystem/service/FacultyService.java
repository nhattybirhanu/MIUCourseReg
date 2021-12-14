package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Faculty;

import java.util.List;

public interface FacultyService {
//    Faculty getFaculty(Integer id);
    Faculty save(Faculty faculty);
    List<Faculty> getAllFaculty();
    void delete(Integer id);
}
