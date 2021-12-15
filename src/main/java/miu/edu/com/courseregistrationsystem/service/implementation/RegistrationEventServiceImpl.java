package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
import miu.edu.com.courseregistrationsystem.domain.RegistrationGroup;
import miu.edu.com.courseregistrationsystem.domain.RegistrationStatus;
import miu.edu.com.courseregistrationsystem.exception.NotFoundException;
import miu.edu.com.courseregistrationsystem.repository.RegistrationEventRepository;
import miu.edu.com.courseregistrationsystem.repository.RegistrationGroupRepository;
import miu.edu.com.courseregistrationsystem.service.RegistrationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Service
@Transactional
public class RegistrationEventServiceImpl implements RegistrationEventService {
    @Autowired
    RegistrationEventRepository registrationEventRepository;
    @Autowired
    RegistrationGroupRepository groupRepository;

    @Override
    public RegistrationEvent getRegistrationEvent(Integer id) {
        Optional<RegistrationEvent>
        optional= registrationEventRepository.findById(id);
        return optional.orElseThrow();
    }

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

    @Override
    public RegistrationEvent addRegGroup(int event_id,int group_id) {
            Optional<RegistrationGroup> registrationGroupOptional=groupRepository.findById(group_id);
            RegistrationGroup registrationGroup=registrationGroupOptional.orElseThrow(() -> new NotFoundException("Registration group is not Found"));
            Optional<RegistrationEvent> event=registrationEventRepository.findById(event_id);
            event.ifPresentOrElse(new Consumer<RegistrationEvent>() {
                @Override
                public void accept(RegistrationEvent registrationEvent) {
                    if (registrationEvent.getStatus()== RegistrationStatus.OPEN){
                       Set<RegistrationGroup> groups= registrationEvent.getGroup();
                        groups.add(registrationGroup);
                        registrationEvent.setGroup(groups);
                        registrationEventRepository.save(registrationEvent);
                    } else
                    new NotFoundException("Registration event is not closed");

                }
            },()->{
                new NotFoundException("Registration event is not found");
            });
        return event.orElseThrow();
    }
}
