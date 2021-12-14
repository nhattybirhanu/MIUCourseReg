package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.Admin;
import miu.edu.com.courseregistrationsystem.domain.Faculty;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.service.AdminService;
import miu.edu.com.courseregistrationsystem.service.FacultyService;
import miu.edu.com.courseregistrationsystem.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements StudentService, FacultyService, AdminService {

    @Override
    public Admin save(Admin admin) {
        return null;
    }

    @Override
    public Faculty save(Faculty faculty) {
        return null;
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return null;
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public List<Student> getAllStudent() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

}
