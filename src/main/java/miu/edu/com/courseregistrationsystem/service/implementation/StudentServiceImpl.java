package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.RegistrationRequest;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.repository.StudentRepository;
import miu.edu.com.courseregistrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public List<RegistrationRequest> getRegistration(int id) {
        return studentRepository.findById(id).orElseThrow().getRegistrationRequests();
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }


    @Override
    public void delete(Integer id) {

    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).orElseThrow();
    }
}
