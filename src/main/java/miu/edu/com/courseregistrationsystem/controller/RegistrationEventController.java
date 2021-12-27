package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
import miu.edu.com.courseregistrationsystem.domain.RegistrationStatus;
import miu.edu.com.courseregistrationsystem.dto.EventDto;
import miu.edu.com.courseregistrationsystem.service.RegistrationEventService;
import miu.edu.com.courseregistrationsystem.util.DateAndCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("admin/registrationevents")
public class RegistrationEventController {

    @Autowired
    RegistrationEventService registrationEventService;


    @GetMapping(value = "/get/{id}")
    public RegistrationEvent getRegistrationEvent(@PathVariable Integer id) {
        return registrationEventService.getRegistrationEvent(id);
    }

    @PostMapping(value = "/create")
    public RegistrationEvent create(@RequestBody RegistrationEvent registrationEvent) {
        if(registrationEvent.getEndDateTime().isBefore(LocalDateTime.now())){
            registrationEvent.setStatus((RegistrationStatus.CLOSED));
        }else if((registrationEvent.getStartDateTime().isBefore(LocalDateTime.now()))&&(registrationEvent.getEndDateTime().isAfter(LocalDateTime.now()))){
            registrationEvent.setStatus(RegistrationStatus.OPEN);
        }else{
            registrationEvent.setStatus(RegistrationStatus.PENDING);
        }

        return registrationEventService.save(registrationEvent);


//        registrationEvent.setStatus(registrationEvent.getStartDateTime().isAfter(LocalDateTime.now())?RegistrationStatus.PENDING:RegistrationStatus.CLOSED);
//        return registrationEventService.save(registrationEvent);
    }

    @GetMapping(value = "/all")
    public List<RegistrationEvent> getAllRegistrationEvent() {
        return registrationEventService.getAllRegistrationEvent();
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Integer id) {
        registrationEventService.delete(id);
    }

    @PatchMapping("updatestatus/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") int id,@RequestBody RegistrationStatus status){
return ResponseEntity.ok(registrationEventService.updateStatus(id,status));
    }
    @PatchMapping("/addgroup/{id}")
    public ResponseEntity<?> addGroup(@PathVariable("id")int id, @RequestBody int group_id){

        return ResponseEntity.ok(registrationEventService.addRegGroup(id,group_id));
    }
}
