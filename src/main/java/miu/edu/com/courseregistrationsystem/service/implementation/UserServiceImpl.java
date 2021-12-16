package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.repository.AdminRepository;
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

import java.util.*;

@Service
public class UserServiceImpl implements StudentService, FacultyService, AdminService , UserDetailsService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Admin save(Admin admin) {
        return userRepository.save(admin);
    }

    @Override
    public Faculty save(Faculty faculty) {
        return userRepository.save(faculty);

    }

    @Override
    public List<Faculty> getAllFaculty() {
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
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Student findById(int id) {
        return null;
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
