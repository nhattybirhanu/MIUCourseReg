package miu.edu.com.courseregistrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper=false)

@NoArgsConstructor
@AllArgsConstructor
public class Faculty extends User {

    private String title;

    public Faculty(String firstName, String lastName, String email, String password, Role role, String title) {
        super(firstName, lastName, email, password, role);
        this.title = title;
    }
}
