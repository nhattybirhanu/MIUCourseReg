package miu.edu.com.courseregistrationsystem.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity
public class AcademicBlock {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String semester;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @OneToMany
    private List<CourseOffering> courseOfferings = new ArrayList<>();

}
