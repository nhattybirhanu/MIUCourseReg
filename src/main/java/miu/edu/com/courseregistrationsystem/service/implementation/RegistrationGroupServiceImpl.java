package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.repository.RegistrationGroupRepository;
import miu.edu.com.courseregistrationsystem.service.RegistrationGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationGroupServiceImpl implements RegistrationGroupService {
    private RegistrationGroupRepository registrationGroupRepository;

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
            r.addStudent(student);

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
            r.addBlock(block);

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
                r.removeStudent(groupId,studentId);

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
                r.removeBlock(groupId,block);

            }, () -> {
                new IllegalArgumentException("The Block is not exist in this Group");
            });
        }, () -> {
            new IllegalArgumentException("This Group is not exist");

        });

    }

    @Override
    public RegistrationGroup create(RegistrationGroup registrationGroup) {
        registrationGroupRepository.save(registrationGroup);
        return null;
    }

}
