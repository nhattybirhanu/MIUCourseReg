package miu.edu.com.courseregistrationsystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


public enum Role {
    ADMIN,FACULTY,STUDENT

}

