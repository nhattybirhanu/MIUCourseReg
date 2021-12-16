package miu.edu.com.courseregistrationsystem.dto;

import lombok.Data;
import miu.edu.com.courseregistrationsystem.domain.Role;

@Data
public class LoginDTO {
    private String email;
    private String password;
    private String token;
    private String role;
}
