package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.repository.AcademicBlockRepository;
import miu.edu.com.courseregistrationsystem.repository.StudentRepository;
import miu.edu.com.courseregistrationsystem.service.implementation.RegistrationGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/registrationGroup")
public class RegistrationGroupController {


    @Autowired
    private RegistrationGroupServiceImpl registrationGroupService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicBlockRepository academicBlockRepository;



    @PostMapping(value = "student/add")
    public Student addStudent(@RequestBody int groupId, Student student) {
        studentRepository.findById(student.getId()).ifPresent(c ->{
            throw new RuntimeException();
        });
        return studentRepository.save(student);
    }

    @RequestMapping(value = "student/remove/{id}", method = RequestMethod.GET)
    public void removeStudent(@PathVariable int groupId, int studentId) {
        this.registrationGroupService.removeStudent(groupId, studentId);
    }

    @PostMapping(value = "block/add")
    public AcademicBlock addBlock(@RequestBody int groupId, AcademicBlock block) {
        academicBlockRepository.findById(block.getId()).ifPresent(c ->{
            throw new RuntimeException();
        });
        return academicBlockRepository.save(block);
    }

    @RequestMapping(value = "block/remove/{id}", method = RequestMethod.GET)
    public void removeBlock(@PathVariable int groupId, AcademicBlock block) {
        this.registrationGroupService.removeBlock(groupId, block);


    }
}
