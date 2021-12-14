package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.service.implementation.CourseOfferingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseoffering")
public class CourseOfferingController {

    @Autowired
    CourseOfferingServiceImpl courseOfferingService;


//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CourseOffering courseOffering) {
//     if(id.equals(courseOffering.getId())){
//         return ResponseEntity.ok(courseOfferingService.update(courseOffering));
//     } else{
//         return ResponseEntity.badRequest().build();
//     }
//    }

//    @GetMapping(value = "/get/{id}")
//    public CourseOffering getCourseOffering(@PathVariable Integer id) {
//        return courseOfferingService.getCourseOffering(id);
//    }
//
//    @GetMapping(value = "/save")
//    public CourseOffering save(@RequestBody CourseOffering courseOffering) {
//        return courseOfferingService.save(courseOffering);
//    }
//
//    @GetMapping("/all")
//    public List<CourseOffering> getAllCourseOffering() {
//        return courseOfferingService.getAllCourseOffering();
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable Integer id) {
//        courseOfferingService.delete(id);
//    }
}
