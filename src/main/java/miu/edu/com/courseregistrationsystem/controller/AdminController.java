package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/all")
    public ResponseEntity<?> all(){
        return ResponseEntity.ok(userService.all());
    }
}
