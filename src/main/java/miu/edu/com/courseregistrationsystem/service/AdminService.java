package miu.edu.com.courseregistrationsystem.service;

import miu.edu.com.courseregistrationsystem.domain.Admin;
import miu.edu.com.courseregistrationsystem.domain.User;

import java.util.List;

public interface AdminService {
     Admin save(Admin admin);
     List<Admin> all();
}
