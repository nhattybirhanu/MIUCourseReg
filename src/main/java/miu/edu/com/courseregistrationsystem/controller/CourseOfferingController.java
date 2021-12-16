package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.dto.CourseOfferingDto;
import miu.edu.com.courseregistrationsystem.service.CourseOfferingService;
import miu.edu.com.courseregistrationsystem.service.implementation.CourseOfferingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/courseoffering")
public class CourseOfferingController {

    @Autowired
    CourseOfferingService courseOfferingService;


//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody CourseOffering courseOffering) {
//     if(id.equals(courseOffering.getId())){
//         return ResponseEntity.ok(courseOfferingService.update(courseOffering));
//     } else{
//         return ResponseEntity.badRequest().build();
//     }
//    }

    @GetMapping(value = "/get/{id}")
    public CourseOffering findById(@PathVariable Integer id) {
        return courseOfferingService.findById(id);
    }

    @PostMapping(value = "/create")
    public CourseOffering create(@RequestBody CourseOfferingDto courseOfferingDto) {
        return courseOfferingService.create(courseOfferingDto);
    }

    @GetMapping("/all")
    public Page<CourseOffering> findAll(Pageable pageable) {
        return courseOfferingService.findAll(pageable);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        courseOfferingService.delete(id);
    }
}
