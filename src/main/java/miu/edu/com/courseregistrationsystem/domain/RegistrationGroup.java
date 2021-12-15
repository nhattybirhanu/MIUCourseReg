package miu.edu.com.courseregistrationsystem.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationGroup {

    @Id
    @GeneratedValue
    private int id;
    @Column(nullable = false)
        private  String title;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<Student> student = new ArrayList<>();
    @JoinColumn(name = "academic_block_id")

    @OneToMany(cascade = CascadeType.ALL)
    private List<AcademicBlock> blocks = new ArrayList<>();

}
