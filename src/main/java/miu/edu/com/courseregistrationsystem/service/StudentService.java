package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Registration;
import miu.edu.com.courseregistrationsystem.domain.RegistrationRequest;
import miu.edu.com.courseregistrationsystem.domain.Student;

import java.util.List;

public interface StudentService {
//    Student getStudent(Integer id);
    Student save(Student student);
    List<RegistrationRequest> getRegistration(int id);
    List<Student> getAllStudent();
    void delete(Integer id);
    Student findById(int id);
}
