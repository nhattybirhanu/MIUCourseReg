package miu.edu.com.courseregistrationsystem.dto;

import lombok.Data;
import miu.edu.com.courseregistrationsystem.domain.Address;
import miu.edu.com.courseregistrationsystem.domain.Role;

@Data
public class UserRegistrationDTO {

    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String password;
    Role role;
    private Address mailingAddress;
    private Address homeAddress;

}
