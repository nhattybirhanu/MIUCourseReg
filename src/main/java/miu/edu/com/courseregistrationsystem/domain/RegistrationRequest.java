package miu.edu.com.courseregistrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    @Id
    @GeneratedValue
    private int id;
    private int priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Student student;
    boolean accepted;
    @ManyToOne()
    @JoinColumn(name = "course_offering_id")
    @JsonIgnore
    private CourseOffering courseOffering;

    public RegistrationRequest(Student student, boolean accepted, CourseOffering courseOffering) {
        this.student = student;
        this.accepted = accepted;
        this.courseOffering = courseOffering;
    }
}
