package miu.edu.com.courseregistrationsystem;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class StudentRegistrationSystemApplicationTest {
@Test

    public void  test_1(){
    log.info("Testing phase");
    log.info("Testing phase 2");

    assertEquals(true,true);

}
}