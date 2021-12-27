package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.Security.JWTUtil;
import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.dto.LoginDTO;
import miu.edu.com.courseregistrationsystem.dto.UserRegistrationDTO;
import miu.edu.com.courseregistrationsystem.service.implementation.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/signup/student")
    public ResponseEntity<?> signupStudent(@RequestBody UserRegistrationDTO registrationDTO){
            registrationDTO.setRole(Role.STUDENT);
            Student student=
            userService.save((Student) convertToEntity(registrationDTO));
        return ResponseEntity.ok(student);
    }

    @PostMapping("/signup/faculty")
    public ResponseEntity<?> signupFaculty(@RequestBody UserRegistrationDTO registrationDTO){
        registrationDTO.setRole(Role.FACULTY);
        Faculty faculty=
                userService.save((Faculty) convertToEntity(registrationDTO));
        return ResponseEntity.ok(faculty);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> signupAdmin(@RequestBody UserRegistrationDTO registrationDTO){
        registrationDTO.setRole(Role.ADMIN);
        Admin admin=
                userService.save((Admin) convertToEntity(registrationDTO));
        return ResponseEntity.ok(admin);
    }
    @GetMapping("/")
    public String hello(){
        return  "Hello";
    }

    private User convertToEntity(UserRegistrationDTO userDto) {

        if (userDto.getRole()==Role.STUDENT){
            return modelMapper.map(userDto,Student.class);
        } else if (userDto.getRole()==Role.FACULTY){
            return modelMapper.map(userDto,Faculty.class);
        } else {
            return modelMapper.map(userDto,Admin.class);
        }


    }
    @PostMapping("/login")
    public ResponseEntity<?> loginauth(@RequestBody LoginDTO loginDTO) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));

        } catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }
        UserDetails userDetails=userService.loadUserByUsername(loginDTO.getEmail());
        loginDTO.setPassword(null);
        String jwt=jwtUtil.generateToken(userDetails);
        loginDTO.setToken(jwt);
        loginDTO.setRole(userDetails.getAuthorities().toString());
        return ResponseEntity.ok(loginDTO);
    }
    @GetMapping("/email")
    public String emailtest(){
        Student student=userService.findById(2);
       /// emailService.sendSignUpConfirmation(student);
        return "Email is sent";
    }


}
