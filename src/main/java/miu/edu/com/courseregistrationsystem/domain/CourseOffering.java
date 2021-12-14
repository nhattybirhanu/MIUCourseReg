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
    private int totalSeat;
    @Transient
    private  int availableSeat;

    @OneToMany
    private List<RegistrationRequest> registrationRequests = new ArrayList<>();

    @OneToOne
    private Course course;

    @OneToMany
    private List<Faculty> faculty = new ArrayList<>();
    @OneToMany
    private List<Student> student = new ArrayList<>();


    public void addStudent(User s) {
    }

    public int getAvailableSeat() {
        return totalSeat-registrationRequests.size();
    }
}

