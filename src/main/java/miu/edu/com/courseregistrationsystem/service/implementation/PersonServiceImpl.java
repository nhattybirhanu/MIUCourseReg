package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.Person;
import miu.edu.com.courseregistrationsystem.repository.PersonRepository;
import miu.edu.com.courseregistrationsystem.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository personRepository;
//    @Override
//    public Person getPerson(Integer id) {
//        return personRepository.getById(id);
//    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        personRepository.deleteById(id);

    }
}
