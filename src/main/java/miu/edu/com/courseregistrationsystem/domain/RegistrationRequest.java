package miu.edu.com.courseregistrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    @Id
    @GeneratedValue
    private int id;
    private int priority;

    @ManyToOne
    private Student student;

    @ManyToOne
    private CourseOffering courseOffering;

}
