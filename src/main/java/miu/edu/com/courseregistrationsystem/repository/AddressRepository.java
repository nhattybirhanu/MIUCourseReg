package miu.edu.com.courseregistrationsystem.repository;

import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<CourseOffering, Integer> {
}
