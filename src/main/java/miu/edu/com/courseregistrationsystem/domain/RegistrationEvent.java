package miu.edu.com.courseregistrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationEvent {

    @Id
    @GeneratedValue
    private int id;
    @OneToMany
    private List<RegistrationGroup> group = new ArrayList<>();



    public void addRegistrationGroup(int RegistrationGroup) {

    }

}
