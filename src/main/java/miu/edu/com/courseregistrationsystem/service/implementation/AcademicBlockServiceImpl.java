package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;
import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.domain.RegistrationGroup;
import miu.edu.com.courseregistrationsystem.exception.NotFoundException;
import miu.edu.com.courseregistrationsystem.repository.AcademicBlockRepository;
import miu.edu.com.courseregistrationsystem.repository.CourseOfferingRepository;
import miu.edu.com.courseregistrationsystem.service.AcademicBlockService;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
@Transactional
public class AcademicBlockServiceImpl implements AcademicBlockService {
    @Autowired
    private AcademicBlockRepository academicBlockRepository;
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    public AcademicBlockServiceImpl(AcademicBlockRepository academicBlockRepository) {
        this.academicBlockRepository = academicBlockRepository;
    }

    @Override
    public Optional<AcademicBlock> getBlockById(int courseOfferingId) {
        return academicBlockRepository.findById(courseOfferingId);

    }

    @Override
    public AcademicBlock save(AcademicBlock academicBlock) {
        return academicBlockRepository.save(academicBlock);
    }

    @Override
    public List<AcademicBlock> allBlocks() {
        return academicBlockRepository.findAll();
    }

    @Override
    public void removeCourseOffering(int id) {

    }

    @Override
    public AcademicBlock batchCourseAdd(int id, int[] course_offering) {
        Optional<AcademicBlock> registrationGroupOptional=academicBlockRepository.findById(id);
        registrationGroupOptional.ifPresentOrElse(new Consumer<AcademicBlock>() {
            @Override
            public void accept(AcademicBlock registrationGroup) {
                List courses_ids_list= ArrayHelper.toList(course_offering);
                List<CourseOffering> courseSet=registrationGroup.getCourseOfferings();
                courseSet=courseSet.stream().filter(course->
                        courses_ids_list.contains(course.getId())).collect(Collectors.toList());
                List<CourseOffering> courseList=courseOfferingRepository.findAllById(courses_ids_list);
                courseSet.addAll(courseList);
                registrationGroup.setCourseOfferings(courseSet);
                academicBlockRepository.save(registrationGroup);

            }
        },()->{
            new NotFoundException("Registration group is not found for "+id);
        });
        return registrationGroupOptional.orElseThrow();    }


}
