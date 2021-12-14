package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;

import java.util.List;

public interface RegistrationEventService {
//    RegistrationEvent getRegistrationEvent(Integer id);
    RegistrationEvent save(RegistrationEvent registrationEvent);
    List<RegistrationEvent> getAllRegistrationEvent();
    void delete(Integer id);
}
