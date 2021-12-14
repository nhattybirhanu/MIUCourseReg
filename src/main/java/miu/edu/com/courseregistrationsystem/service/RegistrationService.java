package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Registration;

import java.util.List;

public interface RegistrationService {
//    Registration getRegistration(Integer id);
    Registration save(Registration registration);
    List<Registration> getAllRegistrationService();
    void delete(Integer id);
}
