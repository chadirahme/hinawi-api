package com.hinawi.api.services.impl;

import com.hinawi.api.converter.UserConverter;
import com.hinawi.api.domains.*;
import com.hinawi.api.dto.UserDto;
import com.hinawi.api.repository.*;
import com.hinawi.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CustomersRepository customersRepository;
    @Autowired
    ProspectiveRepository prospectiveRepository;
    @Autowired
    VendorsRepository vendorsRepository;
    @Autowired
    StudentsRepository studentsRepository;
    @Autowired
    WebMessagesRepository webMessagesRepository;
    @Autowired
    WebDashboardRepository webDashboardRepository;

    @Override
    public UserDto loginUser(UserDto userDto) {

        return UserConverter.entityToDto(usersRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword()));
    }

    @Override
    public Users loginUsers(Users users) {
        return usersRepository.findByEmailAndPassword(users.getEmail(), users.getPassword());
    }

    @Override
    public List<Customers> getCustomers() {
        return customersRepository.findAll();
    }

    @Override
    public List<Prospective> getProspectives() {
        return prospectiveRepository.findAll();
    }

    @Override
    public List<Vendors> getVendors() {
        return vendorsRepository.findAll();
    }

    @Override
    public List<Vendors> getVendorsBalance() {
        return vendorsRepository.findBalance();
    }

    @Override
    public List<Customers> getCustomersBalance() {
        return customersRepository.findBalance();
    }

    @Override
    public List<WebMessages> getWebMessages() {
        return webMessagesRepository.findAll();
    }

    @Override
    public List<Students> getStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public Students addStudents(Students students) {
        return studentsRepository.save(students);
    }
    @Override
    public Integer addListStudents(List<Students> students) {
       // Iterable<Students> iterable = students;
         studentsRepository.saveAll(students);
         return students.size();
    }


    @Override
    public WebMessages addWebMessages(WebMessages webMessages) {
        return webMessagesRepository.save(webMessages);
    }

    @Override
    public WebDashboard addWebDashboard(WebDashboard webDashboard) {
        return webDashboardRepository.save(webDashboard);
    }

    @Override
    public WebDashboard deleteWebDashboard(WebDashboard webDashboard) {
        List<WebDashboard> lst= webDashboardRepository.findByUserIdAndDashName(webDashboard.getUserId() , webDashboard.getDashName());
        webDashboardRepository.deleteAll(lst);
        return null;
    }

    @Override
    public List<WebDashboard> getUserDashboards(Integer userid) {
        return webDashboardRepository.findByUserIdOrderByDashOrder(userid);
    }

    @Override
    public WebDashboard getWebDashboardByName(WebDashboard webDashboard) {
        return webDashboardRepository.findByDashName(webDashboard.getDashName());
    }
}