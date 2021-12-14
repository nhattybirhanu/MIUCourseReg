package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;
import miu.edu.com.courseregistrationsystem.domain.Student;

public interface RegistrationGroupService {
    void request(int groupId,int studentId,int blockId, int courseOfferingId, int priority);
    void addStudent(int groupId, Student student);
    void addBlock(int groupId, AcademicBlock block);
    void removeStudent(int groupId, int studentId);
    void removeBlock(int groupId, AcademicBlock block);

}
