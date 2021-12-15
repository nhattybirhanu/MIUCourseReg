package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Registration;
import miu.edu.com.courseregistrationsystem.dto.RegistrationRequestDTO;
import miu.edu.com.courseregistrationsystem.dto.RequestedCourseDTO;

import java.util.List;

public interface RegistrationService {
//    Registration getRegistration(Integer id);
    Registration save(Registration registration);
    List<Registration> getAllRegistrationService();
    RegistrationRequestDTO request(RegistrationRequestDTO registrationRequestDTO);
    void delete(Integer id);
}
