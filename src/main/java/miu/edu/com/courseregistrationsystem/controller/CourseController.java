package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.Course;
import miu.edu.com.courseregistrationsystem.service.implementation.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/courses")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
//
//    @GetMapping("/{id}")
//    public Course getCourse(@PathVariable Integer id) {
//        return courseService.getCourse(id);
//    }

    @PostMapping("/create")
    public Course save(@RequestBody Course course) {
        return courseService.save(course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.delete(id);
    }

}
