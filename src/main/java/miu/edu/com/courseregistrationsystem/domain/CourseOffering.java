package miu.edu.com.courseregistrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseOffering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String role;
    private int availableSeat;

    @OneToMany
    private List<RegistrationRequest> registrationRequests = new ArrayList<>();

    @OneToOne
    private Course course;

    @OneToMany
    private List<User> faculity = new ArrayList<>();


    public void addStudent(User s) {
    }
}

