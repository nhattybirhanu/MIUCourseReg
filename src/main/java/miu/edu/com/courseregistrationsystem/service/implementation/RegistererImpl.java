package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.service.Registerer;

import java.util.*;
import java.util.stream.Collectors;

public class RegistererImpl implements Registerer {

    @Override
    public void process(long eventId) {
        RegistrationEvent registrationEvent = null;

        //map<blockId,map<studentId,List<course>>>
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
            while(studentPriorityQueue!=null){
                PriorityQueue<StudentPriorityWrapper> temp = new PriorityQueue<>();
                while (!studentPriorityQueue.isEmpty()){
                    Student student = studentPriorityQueue.remove().getStudent();
                    PriorityQueue<RegistrationRequest> requests=memo.get(block.getId()).get(student.getId());
                    int total = block.getCourseOfferings().size();
                    int count = 0;
                    do{
                        count++;
                        RegistrationRequest request=requests.remove();
                        if(request.getCourseOffering().getAvailableSeat()>0){
                            //register
                            //decrease available seats
                            temp.add(new StudentPriorityWrapper(total-count,student));
                            break;
                        }
                    }
                    while(true);
                }
                studentPriorityQueue=temp;
            }
        }

    }

    private Map<Integer,Map<Integer,PriorityQueue<RegistrationRequest>>> init(RegistrationEvent registrationEvent) {
        Map<Integer,Map<Integer,PriorityQueue<RegistrationRequest>>> memo = new HashMap<>();

        for(RegistrationGroup group: registrationEvent.getGroup()){
            for(AcademicBlock ab : group.getBlocks()){
                for(CourseOffering of : ab.getCourseOfferings()){
                    for(Student student : of.getStudent()){
                        if(!memo.containsKey(student.getId()))memo.put(student.getId(),new HashMap<>());

                        Map<Integer,PriorityQueue<RegistrationRequest>> temp = memo.get(ab.getId());
                        if(!temp.containsKey(student.getId())){
                            PriorityQueue<RegistrationRequest> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(RegistrationRequest::getPriority));
                            temp.put(student.getId(),priorityQueue);
                            for(RegistrationRequest request : of.getRegistrationRequests()){
                                priorityQueue.add(request);
                            }
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
