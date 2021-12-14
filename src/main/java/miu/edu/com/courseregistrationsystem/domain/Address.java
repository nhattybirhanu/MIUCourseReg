package miu.edu.com.courseregistrationsystem.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
@Data
public class Address {

    @Id
    @GeneratedValue
    private int id;
    private String street;
    private String city;
    private String postalCode;
    private String state;
}
