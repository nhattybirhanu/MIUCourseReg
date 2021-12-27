package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.Faculty;
import miu.edu.com.courseregistrationsystem.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {

    @Autowired
    UserServiceImpl userService;
    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(userService.getAllFaculty());
    }

//    @GetMapping(value = "/get/{id}")
//    public Faculty getFaculty(@PathVariable Integer id) {
//       return facultyService.getFaculty(id);
//    }

}
