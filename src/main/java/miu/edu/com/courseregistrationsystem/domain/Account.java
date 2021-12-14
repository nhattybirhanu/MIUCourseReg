package miu.edu.com.courseregistrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(name = "username")
        private String username;

        @Column(name = "password")
        @JsonIgnore
        private String password;

        @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
        private Set<Role> roles;
}
