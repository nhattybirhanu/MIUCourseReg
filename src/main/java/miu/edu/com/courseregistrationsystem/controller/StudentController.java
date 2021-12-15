package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.service.StudentService;
import miu.edu.com.courseregistrationsystem.service.implementation.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;
    @GetMapping("/all")
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());



    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") int id){
        return ResponseEntity.ok(studentService.findById(id));

    }

<<<<<<< HEAD
    @GetMapping(value = "/get/{id}")
    public Student findById(@PathVariable int id) {
        return studentService.findById(id);
    }
=======

>>>>>>> 47d0020c2ba4fcad2ee66db4fd93ff99ea3e39e8
}
