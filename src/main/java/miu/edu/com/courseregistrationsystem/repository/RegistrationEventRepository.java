package miu.edu.com.courseregistrationsystem.repository;

import miu.edu.com.courseregistrationsystem.domain.RegistrationEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RegistrationEventRepository extends JpaRepository<RegistrationEvent, Integer> {
}
