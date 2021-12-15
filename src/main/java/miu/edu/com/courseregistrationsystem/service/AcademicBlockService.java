package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;

import java.util.List;
import java.util.Optional;


public interface AcademicBlockService {
    Optional<AcademicBlock> getBlockById(int courseOfferingId);
    AcademicBlock save(AcademicBlock academicBlock);
    List<AcademicBlock> allBlocks();
    void removeCourseOffering(int id);
     AcademicBlock batchCourseAdd(int id,int [] course_offering);
}
