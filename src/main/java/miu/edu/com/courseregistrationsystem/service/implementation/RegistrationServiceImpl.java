package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
import miu.edu.com.courseregistrationsystem.domain.RegistrationGroup;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.dto.RegistrationRequestDTO;
import miu.edu.com.courseregistrationsystem.dto.RequestedCourseDTO;
import miu.edu.com.courseregistrationsystem.exception.NotFoundException;
import miu.edu.com.courseregistrationsystem.repository.RegistrationEventRepository;
import miu.edu.com.courseregistrationsystem.repository.RegistrationGroupRepository;
import miu.edu.com.courseregistrationsystem.repository.RegistrationRepository;
import miu.edu.com.courseregistrationsystem.domain.Registration;
import miu.edu.com.courseregistrationsystem.repository.StudentRepository;
import miu.edu.com.courseregistrationsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    RegistrationRepository registrationRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RegistrationEventRepository registrationEventRepository;

    @Autowired
    RegistrationGroupRepository registrationGroupRepository;

//
//    @Override
//    public Registration getRegistration(Integer id) {
//        return registrationRepository.getById(id);
//    }

    @Override
    public Registration save(Registration registration) {
        return registrationRepository.save(registration);
    }

    @Override
    public List<Registration> getAllRegistrationService() {
        return registrationRepository.findAll();
    }

    @Override
    public RegistrationRequestDTO request(RegistrationRequestDTO registrationRequestDTO) {
        Optional<RegistrationEvent> optionalRegistrationEvent=registrationEventRepository.findById(registrationRequestDTO.getEvent_id());
        optionalRegistrationEvent.ifPresentOrElse(new Consumer<RegistrationEvent>() {
            @Override
            public void accept(RegistrationEvent registrationEvent) {
               Optional<RegistrationGroup>  currentGroup= registrationEvent.getGroup().stream().
                        filter(registrationGroup -> registrationGroup.getId()!=registrationRequestDTO.getGroup_id()).findFirst();
               currentGroup.ifPresentOrElse(new Consumer<RegistrationGroup>() {
                   @Override
                   public void accept(RegistrationGroup registrationGroup) {
                       Optional<Student> optionalStudent= registrationGroup.getStudent().stream().
                               filter(studentX -> studentX.getId()!=registrationRequestDTO.getStudent_id()).findFirst();
                       optionalStudent.ifPresentOrElse(new Consumer<Student>() {
                           @Override
                           public void accept(Student student) {
                               HashMap<Integer,List<RequestedCourseDTO>> requested=registrationRequestDTO.getRequest();
                               Set<Integer> blocks=requested.keySet();
                               for (Integer block_id:blocks){
                                   HashMap<Integer,Integer> priority_and_course=new HashMap<>();
                                   List<RequestedCourseDTO> requestedCourseDTOS=requested.get(block_id);
                                   requestedCourseDTOS.forEach(requestedCourse ->
                                           {
                                               if (!priority_and_course.containsKey(requestedCourse.getPriority())&&!priority_and_course.containsValue(requestedCourse.getCourse_id())) {
                                                   priority_and_course.put(requestedCourse.getPriority(),requestedCourse.getCourse_id());
                                                   requestedCourse.setRequested(true);

                                               }
                                           }

                                   );
                               }
                           }
                       },
                               ()->new NotFoundException("Student is not found for "+registrationRequestDTO.getStudent_id()));


                   }
               },
                       ()->new NotFoundException("Registration group is not found for "+registrationRequestDTO.getGroup_id()));
            }
        },
                ()->new NotFoundException("Registration event is not found for "+registrationRequestDTO.getEvent_id()));


        return registrationRequestDTO;
    }

    @Override
    public void delete(Integer id) {
        registrationRepository.deleteById(id);

    }
}
