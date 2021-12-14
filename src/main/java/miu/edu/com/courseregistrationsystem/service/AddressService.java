package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Address;
import miu.edu.com.courseregistrationsystem.domain.CourseOffering;

import java.util.List;

public interface AddressService {
    //void request(CourseOffering courseOffering, int studentId, int priority);
    void addAddress(Address address);
    void removeAddress(int id);
//    Address getAddress(Integer id);
//    Address save(Address address);
//    List<Address> getAllAddress();
    void delete(Integer id);
}
