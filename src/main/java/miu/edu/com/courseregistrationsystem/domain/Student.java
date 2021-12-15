package miu.edu.com.courseregistrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper=false)

@Data

@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Student extends User {
    @OneToMany(mappedBy = "student")

    List<RegistrationRequest> registrationRequests;
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="mailing_street")),
            @AttributeOverride(name="city", column=@Column(name="mailing_city")),
            @AttributeOverride(name="state", column=@Column(name="mailing_state")),
            @AttributeOverride(name="postalCode", column=@Column(name="mailing_postalCode"))
    })
    private Address mailingAddress;
    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="home_street")),
            @AttributeOverride(name="city", column=@Column(name="home_city")),
            @AttributeOverride(name="state", column=@Column(name="home_state")),
            @AttributeOverride(name="postalCode", column=@Column(name="home_postalCode"))
    })

    private Address homeAddress;

    public Student(String firstName, String lastName, String email, String password, Role role, Address mailingAddress, Address homeAddress) {
        super(firstName, lastName, email, password, role);
        this.mailingAddress = mailingAddress;
        this.homeAddress = homeAddress;
    }


}
