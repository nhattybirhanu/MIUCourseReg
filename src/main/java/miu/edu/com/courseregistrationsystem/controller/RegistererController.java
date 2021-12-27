package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.service.implementation.RegistererImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/processregistration-events")
public class RegistererController {
    @Autowired
    RegistererImpl registerer;

    @PatchMapping("/{id}")
    public void process(@PathVariable  int id){
        registerer.process(id);
    }
}
