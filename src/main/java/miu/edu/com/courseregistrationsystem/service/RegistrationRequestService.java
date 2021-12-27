package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.RegistrationRequest;

import java.util.List;

public interface RegistrationRequestService {
    RegistrationRequest getById(Integer id);
//    RegistrationRequest save(RegistrationRequest registrationRequest);
    List<RegistrationRequest> getAllRegistrationRequest();
    void delete(Integer id);
}
