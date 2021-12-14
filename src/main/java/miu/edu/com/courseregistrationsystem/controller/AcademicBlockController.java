package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;
import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.service.implementation.AcademicBlockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/academicblocks")
public class AcademicBlockController {

    @Autowired
    AcademicBlockServiceImpl academicBlockService;

//
//    @GetMapping("/all")
//    public List<AcademicBlock> getAllAcademicBlock() {
//       return academicBlockService.getAllAcademicBlock();
//    }

//    @PostMapping(value = "/courseOffering/add")
//    public void addCourseOffering(CourseOffering courseOffering) {
//        academicBlockService.addCourseOffering(courseOffering);
//    }

    @RequestMapping(value = "/courseoffering/delete/{id}")
    public void removeCourseOffering(@PathVariable Integer id) {

        academicBlockService.removeCourseOffering(id);
    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?>  getBlockById(@PathVariable Integer id) {
//        Optional<AcademicBlock> result = Optional.ofNullable(academicBlockService.getBlockById(id));
//        if (result.isPresent()) {
//            return ResponseEntity.ok(result.get());
//        } else {
//            return ResponseEntity.badRequest().build();
//        }
//    }

//    @PostMapping("/add")
//    public AcademicBlock save(AcademicBlock academicBlock) {
//      return academicBlockService.save(academicBlock);
//    }

//
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable Integer id) {
//
//        academicBlockService.delete(id);
//    }

}
