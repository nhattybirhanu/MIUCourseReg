package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.repository.AdminRepository;
import miu.edu.com.courseregistrationsystem.repository.FacultyRepository;
import miu.edu.com.courseregistrationsystem.repository.StudentRepository;
import miu.edu.com.courseregistrationsystem.repository.UserRepository;
import miu.edu.com.courseregistrationsystem.service.AdminService;
import miu.edu.com.courseregistrationsystem.service.FacultyService;
import miu.edu.com.courseregistrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.*;

@Service
public class UserServiceImpl implements StudentService, FacultyService, AdminService , UserDetailsService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Override
    public Admin save(Admin admin) {
        return userRepository.save(admin);
    }

    @Override
    public List<Admin> all() {
        return null;
    }


    @Override
    public Faculty save(Faculty faculty) {
        return userRepository.save(faculty);

    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<RegistrationRequest> getRegistration(int id) {
        return null;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public void delete(Integer id) {


    }

    @Override
<<<<<<< HEAD
    public Student findById(int id) {
        return studentRepository.findById(id).orElseThrow();
=======
    public Student studentFindById(int id) {
        Optional<Student>
                optional= studentRepository.findById(id);
        return optional.orElseThrow();
>>>>>>> 59446f549fa922595c58caf2a3dba2231137ceb3
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()))));
    }
    public User findByEmail(String email){
        User user = userRepository.findUserByEmail(email).orElseThrow();

        return user;
    }
}
