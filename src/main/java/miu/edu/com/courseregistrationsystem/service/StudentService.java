package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Registration;
import miu.edu.com.courseregistrationsystem.domain.RegistrationRequest;
import miu.edu.com.courseregistrationsystem.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student findById(int id);
    Student save(Student student);
    List<RegistrationRequest> getRegistration(int id);
    List<Student> getAllStudent();
    void delete(Integer id);
    Student findById(int id);
}
