package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Person;

import java.util.List;

public interface PersonService {
//    Person getPerson(Integer id);
    Person save(Person person);
    List<Person> getAllPerson();
    void delete(Integer id);
}
