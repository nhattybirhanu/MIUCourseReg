package miu.edu.com.courseregistrationsystem.repository;

import miu.edu.com.courseregistrationsystem.domain.Student;
import miu.edu.com.courseregistrationsystem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByEmail(String email);
}
