package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
import miu.edu.com.courseregistrationsystem.service.RegistrationEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/registrationEvents")
public class RegistrationEventController {

    @Autowired
    RegistrationEventService registrationEventService;

//
//    @GetMapping(value = "/get/{id}")
//    public RegistrationEvent getRegistrationEvent(@PathVariable Integer id) {
//        return registrationEventService.getRegistrationEvent(id);
//    }

    @PostMapping(value = "/save")
    public RegistrationEvent save(@RequestBody RegistrationEvent registrationEvent) {
        return registrationEventService.save(registrationEvent);
    }

    @GetMapping(value = "/all")
    public List<RegistrationEvent> getAllRegistrationEvent() {
        return registrationEventService.getAllRegistrationEvent();
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Integer id) {
        registrationEventService.delete(id);
    }
}
