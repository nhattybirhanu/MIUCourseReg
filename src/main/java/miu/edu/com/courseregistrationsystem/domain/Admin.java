package miu.edu.com.courseregistrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Entity
public class Admin extends User {

    public Admin(String firstName, String lastName, String email, String password, Role role) {
        super(firstName, lastName, email, password, role);
    }
}
