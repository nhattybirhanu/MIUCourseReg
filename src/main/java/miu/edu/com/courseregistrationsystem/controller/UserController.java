package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.Role;
import miu.edu.com.courseregistrationsystem.domain.User;
import miu.edu.com.courseregistrationsystem.dto.UserRegistrationDTO;
import miu.edu.com.courseregistrationsystem.service.implementation.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserServiceImpl userService;
    @PostMapping("/signup/student")
    public ResponseEntity<?> signupStudent(@RequestBody UserRegistrationDTO registrationDTO){
            registrationDTO.setRole(Role.STUDENT);

        return ResponseEntity.ok(registrationDTO);
    }

    @PostMapping("/signup/faculty")
    public ResponseEntity<?> signupFaculty(UserRegistrationDTO registrationDTO){

        return null;
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> signupAdmin(UserRegistrationDTO registrationDTO){

        return null;
    }
    @GetMapping("/")
    public String hello(){
        return  "Hello";
    }

    private User convertToEntity(UserRegistrationDTO userDto) {
        User user=null;
        if (userDto.getRole()==Role.STUDENT)
        user= modelMapper.map(userDto,User.class);

        return user;
    }
}
