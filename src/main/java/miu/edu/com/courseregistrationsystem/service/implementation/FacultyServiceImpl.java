package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.Faculty;
import miu.edu.com.courseregistrationsystem.repository.FacultyRepository;
import miu.edu.com.courseregistrationsystem.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    FacultyRepository facultyRepository;
//    @Override
//    public Faculty getFaculty(Integer id) {
//        return facultyRepository.getById(id);
//    }

    @Override
    public Faculty save(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }

    @Override
    public void delete(Integer id) {
        facultyRepository.deleteById(id);

    }
}
