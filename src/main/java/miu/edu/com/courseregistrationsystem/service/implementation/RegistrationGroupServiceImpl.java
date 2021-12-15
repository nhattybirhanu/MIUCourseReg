package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.exception.NotFoundException;
import miu.edu.com.courseregistrationsystem.repository.RegistrationGroupRepository;
import miu.edu.com.courseregistrationsystem.repository.StudentRepository;
import miu.edu.com.courseregistrationsystem.service.RegistrationGroupService;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;

@Service
public class RegistrationGroupServiceImpl implements RegistrationGroupService {
    @Autowired
    private RegistrationGroupRepository registrationGroupRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    public RegistrationGroupServiceImpl(RegistrationGroupRepository registrationGroupRepository) {
        this.registrationGroupRepository = registrationGroupRepository;
    }

    @Override

    public void request(int groupId, int studentId, int blockId, int courseOfferingId, int priority){

        Optional<RegistrationGroup> registrationGroup = registrationGroupRepository.findById(groupId);
        registrationGroup.ifPresentOrElse(r->{
            Optional<Student> student = r.getStudent().stream().filter(s -> s.getId()==studentId).findAny();
            student.ifPresentOrElse(s->{
                Optional<AcademicBlock> block = r.getBlocks().stream().filter(a->a.getId()==blockId).findAny();
                block.ifPresentOrElse(a->{
                    Optional<CourseOffering> courseOffering = a.getCourseOfferings().stream().filter(c->c.getId()==courseOfferingId).findAny();
                    courseOffering.ifPresentOrElse(c->{
                        c.addStudent(s);
                    },() -> {
                        new IllegalArgumentException(" CourseOffering does not exist in this block");

                    });
                },() -> {
                    new IllegalArgumentException(" Block does not exist in this Group");

                });
            },() -> {
                new IllegalArgumentException(" Student does not exist in this block");

            });
        },() -> {
            new IllegalArgumentException(" This group does not found ");

        });
    }

    @Override
    public void addStudent(int groupId, Student student) {
        Optional<RegistrationGroup> registrationGroup = registrationGroupRepository.findById(groupId);
        registrationGroup.ifPresentOrElse(r->{
            r.getStudent().stream().filter(s -> s.getId() ==student.getId() ).findAny()
                    .orElseThrow(()->new IllegalArgumentException("This Student is already exist"));
         //   r.addStudent(student);

          //  return registrationGroupRepository.save(groupId, Student);
        },()->{
            new IllegalArgumentException("This Group is not exist");
        });

    }

    @Override
    public void addBlock(int groupId, AcademicBlock block) {
        Optional<RegistrationGroup> registrationGroup = registrationGroupRepository.findById(groupId);
        registrationGroup.ifPresentOrElse(r->{
            r.getBlocks().stream().filter(b -> b.getId() ==block.getId() ).findAny()
                    .orElseThrow(()->new IllegalArgumentException("This block is already exist"));
           // r.addBlock(block);

        },()->{
            new IllegalArgumentException("This Group is not exist");
        });

    }

    @Override
    public void removeStudent(int groupId, int studentId) {

        Optional<RegistrationGroup> registrationGroup = registrationGroupRepository.findById(groupId);
        registrationGroup.ifPresentOrElse(r-> {
            Optional<Student> student = r.getStudent().stream().filter(s -> s.getId() == studentId).findAny();
            student.ifPresentOrElse(s -> {
             //   r.removeStudent(groupId,studentId);

            }, () -> {
                        new IllegalArgumentException("The student is not exist in this Group");
            });
        }, () -> {
            new IllegalArgumentException("This Group is not exist");

        });

    }

    @Override
    public void removeBlock(int groupId, AcademicBlock block) {

        Optional<RegistrationGroup> registrationGroup = registrationGroupRepository.findById(groupId);
        registrationGroup.ifPresentOrElse(r-> {
            Optional<AcademicBlock> aBlock = r.getBlocks().stream().filter(b -> b.getId() == block.getId()).findAny();
            aBlock.ifPresentOrElse(b -> {
               // r.removeBlock(groupId,block);

            }, () -> {
                new IllegalArgumentException("The Block is not exist in this Group");
            });
        }, () -> {
            new IllegalArgumentException("This Group is not exist");

        });

    }

    @Override
    public RegistrationGroup create(RegistrationGroup registrationGroup) {
        return registrationGroupRepository.save(registrationGroup);

    }

    @Override
    public List<RegistrationGroup> getAll() {
        return registrationGroupRepository.findAll();
    }

    @Override
    public RegistrationGroup addStudentBatch(int group_id, int[] student_ids) {
        Optional<RegistrationGroup> registrationGroupOptional=registrationGroupRepository.findById(group_id);
        registrationGroupOptional.ifPresentOrElse(new Consumer<RegistrationGroup>() {
            @Override
            public void accept(RegistrationGroup registrationGroup) {
                List stu_ids= ArrayHelper.toList(student_ids);

                List<Student> studentList=studentRepository.findAllById(stu_ids);
                System.out.println(studentList);
                List<Student> studentSet=registrationGroup.getStudent();
                studentSet.addAll(studentList);
            }
        },()->{
            new NotFoundException("Registration group is not found for "+group_id);
        });
        return registrationGroupOptional.orElseThrow();
    }

}
