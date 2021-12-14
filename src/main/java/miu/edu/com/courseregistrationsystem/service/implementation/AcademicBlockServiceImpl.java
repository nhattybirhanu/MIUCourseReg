package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.AcademicBlock;
import miu.edu.com.courseregistrationsystem.repository.AcademicBlockRepository;
import miu.edu.com.courseregistrationsystem.service.AcademicBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AcademicBlockServiceImpl implements AcademicBlockService {
    @Autowired
    private AcademicBlockRepository academicBlockRepository;

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

}
