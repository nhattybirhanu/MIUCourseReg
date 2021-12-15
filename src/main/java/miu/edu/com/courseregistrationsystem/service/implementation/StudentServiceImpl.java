package miu.edu.com.courseregistrationsystem.service.implementation;

<<<<<<< HEAD
import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
=======
import miu.edu.com.courseregistrationsystem.domain.RegistrationRequest;
>>>>>>> 47d0020c2ba4fcad2ee66db4fd93ff99ea3e39e8
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.repository.StudentRepository;
import miu.edu.com.courseregistrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Student findById(int id) {
        Optional<Student>
                optional= studentRepository.findById(id);
        return optional.orElseThrow();
    }

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
