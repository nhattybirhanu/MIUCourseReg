package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.service.Register;
import miu.edu.com.courseregistrationsystem.service.RegistrationEventService;
import miu.edu.com.courseregistrationsystem.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RegistererImpl implements Register {
    private RegistrationService registrationService;
    private RegistrationEventService registrationEventService;

    @Autowired
    public RegistererImpl(RegistrationService registrationService, RegistrationEventService registrationEventService) {
        this.registrationService = registrationService;
        this.registrationEventService = registrationEventService;
    }

    @Override
    public void process(int eventId) {
        RegistrationEvent registrationEvent = registrationEventService.getRegistrationEvent(eventId);


        Map<Integer,Map<Integer,PriorityQueue<RegistrationRequest>>> memo = init(registrationEvent);
        PriorityQueue<StudentPriorityWrapper> studentPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(StudentPriorityWrapper::getPriority));
        Set<AcademicBlock> allBlocks = new HashSet<>();
        for(RegistrationGroup group : registrationEvent.getGroup()){
            for(Student student: group.getStudent()){
                studentPriorityQueue.add(new StudentPriorityWrapper(1,student));
            }
            for(AcademicBlock block : group.getBlocks()){
                allBlocks.add(block);
            }
        }

        for(AcademicBlock block: allBlocks){
            PriorityQueue<StudentPriorityWrapper> temp = new PriorityQueue<>(Comparator.comparingInt(StudentPriorityWrapper::getPriority));
            for(int i=0;i<studentPriorityQueue.size();i++) {
                StudentPriorityWrapper student = studentPriorityQueue.remove();
                PriorityQueue<RegistrationRequest> requests=memo.get(block.getId()).get(student.getStudent().getId());
                if(requests==null||requests.isEmpty()){
                    temp.add(student);
                }
                else {
                    int total = block.getCourseOfferings().size();
                    int count = 0;
                    do{
                        count++;
                        RegistrationRequest request=requests.remove();
                        if(request.getCourseOffering().getAvailableSeat()>0){
                            Registration registration = new Registration();
                            registration.setCourseOffering(request.getCourseOffering());
                            registration.setStudent(student.getStudent());
                            registrationService.save(registration);
                            request.getCourseOffering().setAvailableSeat(request.getCourseOffering().getAvailableSeat()-1);
                            temp.add(new StudentPriorityWrapper(total-count+student.getPriority(),student.getStudent()));
                            break;
                        }
                    }
                    while(true);
                }
            }
            studentPriorityQueue=temp;
        }
    }

    private Map<Integer,Map<Integer,PriorityQueue<RegistrationRequest>>> init(RegistrationEvent registrationEvent) {
        Map<Integer,Map<Integer,PriorityQueue<RegistrationRequest>>> memo = new HashMap<>();

        for(RegistrationGroup group: registrationEvent.getGroup()){
            for(AcademicBlock ab : group.getBlocks()){
                for(CourseOffering of : ab.getCourseOfferings()){
                    for(RegistrationRequest request: of.getRegistrationRequests()){
                        Student student = request.getStudent();
                        if(!memo.containsKey(ab.getId()))memo.put(ab.getId(),new HashMap<>());

                        Map<Integer,PriorityQueue<RegistrationRequest>> temp = memo.get(ab.getId());
                        if(!temp.containsKey(student.getId())){
                            PriorityQueue<RegistrationRequest> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(RegistrationRequest::getPriority));
                            temp.put(student.getId(),priorityQueue);
                            priorityQueue.add(request);
                        }
                        else{
                            temp.get(student.getId()).add(request);
                        }
                    }
                }
            }
        }
        return memo;
    }
    private class StudentPriorityWrapper{
        private int priority;
        private Student student;

        public StudentPriorityWrapper(int priority, Student student) {
            this.priority = priority;
            this.student = student;
        }

        public int getPriority() {
            return priority;
        }

        public Student getStudent() {
            return student;
        }
    }
}
