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
    public Students addStudents(Students students) {
        return studentsRepository.save(students);
    }
    @Override
    public Integer addListStudents(List<Students> students) {
       // Iterable<Students> iterable = students;
         studentsRepository.saveAll(students);
         return students.size();
    }
}