package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;
import miu.edu.com.courseregistrationsystem.domain.RegistrationGroup;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.repository.AcademicBlockRepository;
import miu.edu.com.courseregistrationsystem.repository.StudentRepository;
import miu.edu.com.courseregistrationsystem.service.implementation.RegistrationGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/registrationgroup")
public class RegistrationGroupController {


    @Autowired
    private RegistrationGroupServiceImpl registrationGroupService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicBlockRepository academicBlockRepository;

    @PostMapping("/create")
    public RegistrationGroup registrationGroup(@RequestBody  RegistrationGroup group){
return registrationGroupService.create(group);
    }

    @GetMapping("/all")
    public List<RegistrationGroup> getAll(){
        return registrationGroupService.getAll();
    }

    @PutMapping(value = "/add/students/{id}")
    public ResponseEntity<?> addStudent(@PathVariable("id") int groupId, @RequestBody int [] student_ids) {

        return ResponseEntity.ok(registrationGroupService.addStudentBatch(groupId,student_ids));
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
