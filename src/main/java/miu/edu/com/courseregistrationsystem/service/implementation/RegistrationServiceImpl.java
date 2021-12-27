package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.dto.RegistrationRequestDTO;
import miu.edu.com.courseregistrationsystem.dto.RequestedCourseDTO;
import miu.edu.com.courseregistrationsystem.exception.NotFoundException;
import miu.edu.com.courseregistrationsystem.repository.*;
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
    @Autowired
    RegistrationRequestRepository registrationRequestRepository;


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
                        filter(registrationGroup -> registrationGroup.getId()==registrationRequestDTO.getGroup_id()).findFirst();
               currentGroup.ifPresentOrElse(new Consumer<RegistrationGroup>() {
                   @Override
                   public void accept(RegistrationGroup registrationGroup) {

                       Optional<Student> optionalStudent= registrationGroup.getStudent().stream().
                               filter(studentX -> studentX.getId()==registrationRequestDTO.getStudent_id()).findFirst();
                       optionalStudent.ifPresentOrElse(new Consumer<Student>() {
                           @Override
                           public void accept(Student student) {


                               HashMap<Integer,List<RequestedCourseDTO>> requested=registrationRequestDTO.getRequest();
                               Set<Integer> blocks=requested.keySet();
                               for (Integer block_id:blocks){
                                   Optional<AcademicBlock> academicBlock=registrationGroup.getBlocks().stream().filter(block -> block.getId()==block_id).findFirst();
                                   academicBlock.ifPresent(new Consumer<AcademicBlock>() {
                                       @Override
                                       public void accept(AcademicBlock academicBlock) {
                                           HashMap<Integer,Integer> priority_and_course=new HashMap<>();
                                           List<RequestedCourseDTO> requestedCourseDTOS=requested.get(block_id);
                                           requestedCourseDTOS.forEach(requestedCourse ->
                                                   {
                                                       Optional<CourseOffering> courseOffering=academicBlock.getCourseOfferings().stream().filter(courseOffering1 -> courseOffering1.getId()==requestedCourse.getCourse_id()).findFirst();
                                                        courseOffering.ifPresent(new Consumer<CourseOffering>() {
                                                            @Override
                                                            public void accept(CourseOffering courseOffering) {

                                                                if (!priority_and_course.containsKey(requestedCourse.getPriority())&&!priority_and_course.containsValue(requestedCourse.getCourse_id()))
                                                                {

                                                                    RegistrationRequest request=null;
                                                                    Optional<RegistrationRequest> requestStream=courseOffering.getRegistrationRequests().stream().filter(
                                                                            registrationRequest->
                                                                                    registrationRequest.getStudent().getId()==student.getId()&&
                                                                                     registrationRequest.getCourseOffering().getId()==requestedCourse.getCourse_id()

                                                                    ).findFirst();
                                                                    request = requestStream.orElseGet(() -> new RegistrationRequest(student, false, courseOffering));
                                                                    if (!request.isAccepted()){
                                                                        request.setPriority(requestedCourse.getPriority());
                                                                        priority_and_course.put(requestedCourse.getPriority(),requestedCourse.getCourse_id());
                                                                        requestedCourse.setRequested(true);
                                                                        registrationRequestRepository.save(request);
                                                                    }
                                                                }
                                                            }
                                                        });
                                                   }

                                           );
                                       }
                                   });

                               }
                           }
                       },
                               ()-> {
                                   throw new NotFoundException("Student is not found for " + registrationRequestDTO.getStudent_id());
                               });


                   }
               },
                       ()-> {
                           throw new NotFoundException("Registration group is not found for " + registrationRequestDTO.getGroup_id());
                       });
            }
        },
                ()-> {
                    throw new NotFoundException("Registration event is not found for " + registrationRequestDTO.getEvent_id());
                });


        return registrationRequestDTO;
    }

    @Override
    public void delete(Integer id) {
        registrationRepository.deleteById(id);

    }
}
