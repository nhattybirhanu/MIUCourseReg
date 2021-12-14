package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.repository.CourseOfferingRepository;
import miu.edu.com.courseregistrationsystem.service.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {
    private CourseOfferingRepository courseOfferingRepository;


    @Autowired
    public CourseOfferingServiceImpl(CourseOfferingRepository courseOfferingRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
    }


    @Override
    public void update(CourseOffering courseOffering) {

    }

    @Override
    public CourseOffering create(CourseOffering courseOffering) {
        return courseOfferingRepository.save(courseOffering);
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
