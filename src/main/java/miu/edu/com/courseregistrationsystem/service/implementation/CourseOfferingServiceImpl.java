package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.*;
import miu.edu.com.courseregistrationsystem.dto.CourseOfferingDto;
import miu.edu.com.courseregistrationsystem.exception.NotFoundException;
import miu.edu.com.courseregistrationsystem.repository.AcademicBlockRepository;
import miu.edu.com.courseregistrationsystem.repository.CourseOfferingRepository;
import miu.edu.com.courseregistrationsystem.repository.CourseRepository;
import miu.edu.com.courseregistrationsystem.repository.UserRepository;
import miu.edu.com.courseregistrationsystem.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AcademicBlockRepository blockRepository;
    @Autowired
    CourseRepository courseRepository;



    @Autowired
    public CourseOfferingServiceImpl(CourseOfferingRepository courseOfferingRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
    }


    @Override
    public void update(CourseOffering courseOffering) {

    }

    @Override
    public CourseOffering create(CourseOfferingDto courseOfferingDto) {
        Optional<User> userOptional=userRepository.findById(courseOfferingDto.getFaculty_id());
        userOptional.ifPresentOrElse(user -> {
            if (user.getRole()== Role.FACULTY){
                Optional<Course> optionalCourse=courseRepository.findById(courseOfferingDto.getCourse_id());
                optionalCourse.ifPresentOrElse(new Consumer<Course>() {
                    @Override
                    public void accept(Course course) {
                          Optional<AcademicBlock> academicBlockOptional=blockRepository.findById(courseOfferingDto.getBlock_id());
                          academicBlockOptional.ifPresentOrElse(new Consumer<AcademicBlock>() {
                              @Override
                              public void accept(AcademicBlock academicBlock) {
                                  CourseOffering courseOffering=new CourseOffering();
                              }
                          },()->{

                              new NotFoundException("Academic block is nof found for "+courseOfferingDto.getBlock_id());

                          });
                    }
                },()->{
                    new NotFoundException("Course is nof found for "+courseOfferingDto.getCourse_id());

                });


            }
            else{
                new NotFoundException("Faculty is nof found for "+courseOfferingDto.getFaculty_id());

            }
        },()->{
            new NotFoundException("User is nof found for "+courseOfferingDto.getFaculty_id());

        });


        return null;
    }


    @Override
    public CourseOffering findById(int id) {
        return null;
    }
    @Override
    public Page<CourseOffering> findAll(Pageable pageable) {
        return courseOfferingRepository.findAll(pageable);
    }

    @Override
    public void delete(int id) {
        courseOfferingRepository.deleteById(id);
    }
}
