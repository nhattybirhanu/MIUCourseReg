package miu.edu.com.courseregistrationsystem.controller;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;
import miu.edu.com.courseregistrationsystem.dto.AcademicBlockDto;
import miu.edu.com.courseregistrationsystem.service.AcademicBlockService;
import miu.edu.com.courseregistrationsystem.util.DateAndCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/academicblocks")
public class AcademicBlockController {

    @Autowired
    AcademicBlockService academicBlockService;


    @GetMapping("/all")
    public List<AcademicBlock> getAllAcademicBlock() {
       return academicBlockService.allBlocks();
    }

//    @PostMapping(value = "/courseOffering/add")
//    public void addCourseOffering(CourseOffering courseOffering) {
//        academicBlockService.addCourseOffering(courseOffering);
//    }

    @RequestMapping(value = "/courseoffering/delete/{id}")
    public void removeCourseOffering(@PathVariable Integer id) {

        academicBlockService.removeCourseOffering(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBlockById(@PathVariable Integer id) {
        Optional<AcademicBlock> result =academicBlockService.getBlockById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public AcademicBlock save(@RequestBody AcademicBlockDto academicBlockDto) {
        LocalDateTime [] localDateTimes= DateAndCodeUtil.getStartAndEndTime(academicBlockDto.getYear(),academicBlockDto.getMonth(),academicBlockDto.getDay(),academicBlockDto.getStartWeek(),academicBlockDto.getEndWeek());
        AcademicBlock academicBlock=new AcademicBlock();
        academicBlock.setStartDate(localDateTimes[0]);
        academicBlock.setEndDate(localDateTimes[1]);
        academicBlock.setCode(DateAndCodeUtil.code(localDateTimes[0],academicBlockDto.getStartWeek(),academicBlockDto.getEndWeek()));
        academicBlock.setName(academicBlockDto.getName());
        academicBlock.setSemester(academicBlockDto.getSemester());
      return academicBlockService.save(academicBlock);
    }
    @PutMapping(value = "add/course/{id}")
    public ResponseEntity<?> addCourse(@PathVariable("id") int groupId, @RequestBody  int [] courses) {

        return ResponseEntity.ok(academicBlockService.batchCourseAdd(groupId,courses));
    }


//
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable Integer id) {
//
//        academicBlockService.delete(id);
//    }

}
