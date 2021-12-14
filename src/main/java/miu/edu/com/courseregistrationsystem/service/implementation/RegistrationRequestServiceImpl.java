package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.RegistrationRequest;
import miu.edu.com.courseregistrationsystem.repository.RegistrationRequestRepository;
import miu.edu.com.courseregistrationsystem.service.RegistrationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegistrationRequestServiceImpl implements RegistrationRequestService {
    @Autowired
    RegistrationRequestRepository registrationRequestRepository;

//    @Override
//    public RegistrationRequest getById(Integer id) {
//        return registrationRequestRepository.getOne(id);
//    }

//    @Override
//    public RegistrationRequest save(RegistrationRequest registrationRequest) {
//        return registrationRequestRepository.save(registrationRequest);
//    }

    @Override
    public List<RegistrationRequest> getAllRegistrationRequest() {
        return registrationRequestRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        registrationRequestRepository.deleteById(id);

    }
}
