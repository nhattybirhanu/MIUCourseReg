package miu.edu.com.courseregistrationsystem.service.implementation;

import miu.edu.com.courseregistrationsystem.domain.Address;
import miu.edu.com.courseregistrationsystem.domain.CourseOffering;
import miu.edu.com.courseregistrationsystem.repository.AddressRepository;
import miu.edu.com.courseregistrationsystem.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
//    @Override
//    public void request(CourseOffering courseOffering, int studentId, int priority) {
//
//
//    }

    @Override
    public void addAddress(Address address) {

    }

    @Override
    public void removeAddress(int id) {

    }
//
//    @Override
//    public Address getAddress(Integer id) {
//        return addressRepository.getById(id);
//    }
//
//    @Override
//    public Address save(Address address) {
//        return addressRepository.save(address);
//    }
//
//    @Override
//    public List<Address> getAllAddress() {
//        return addressRepository.findAll();
//    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);

    }
}
