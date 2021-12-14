package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
import miu.edu.com.courseregistrationsystem.repository.RegistrationEventRepository;
import miu.edu.com.courseregistrationsystem.service.RegistrationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    RegistrationEventRepository registrationEventRepository;

//    @Override
//    public RegistrationEvent getRegistrationEvent(Integer id) {
//        return registrationEventRepository.getById(id);
//    }

    @Override
    public RegistrationEvent save(RegistrationEvent registrationEvent) {
        return registrationEventRepository.save(registrationEvent);
    }

    @Override
    public List<RegistrationEvent> getAllRegistrationEvent() {
        return registrationEventRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        registrationEventRepository.deleteById(id);

    }
}
