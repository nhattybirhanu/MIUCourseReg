package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.dto.RegistrationRequestDTO;
import miu.edu.com.courseregistrationsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students/registration")
public class RegistrationRequestController {
    @Autowired
    RegistrationService registrationService;
    @PostMapping("/")
   public ResponseEntity<?> registration(@RequestBody RegistrationRequestDTO registrationRequestDTO){
        return ResponseEntity.ok( registrationService.request(registrationRequestDTO));
    }
}
