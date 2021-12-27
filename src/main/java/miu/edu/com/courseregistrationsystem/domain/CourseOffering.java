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
    @Column(nullable = false,unique = true)
    private String code;

    private int totalSeat;
    private  int availableSeat;

    @OneToMany(mappedBy ="courseOffering" )

    private List<RegistrationRequest> registrationRequests = new ArrayList<>();

    @JoinColumn(name = "course_id")
    @OneToOne
    private Course course;

    @OneToOne
    private Faculty faculty ;


    public void addStudent(User s) {
    }

    public int getAvailableSeat() {
   //     availableSeat =(int) (totalSeat-registrationRequests.stream().filter(registrationRequest -> registrationRequest.accepted).count());
        return availableSeat;
    }
}

