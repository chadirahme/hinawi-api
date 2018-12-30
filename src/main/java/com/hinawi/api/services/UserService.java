package com.hinawi.api.services;


import com.hinawi.api.domains.*;
import com.hinawi.api.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto loginUser(UserDto userDto);
    Users loginUsers(Users users);
    List<Customers> getCustomers();
    List<Prospective> getProspectives();
    List<Vendors> getVendors();

    List<Vendors> getVendorsBalance();
    List<Customers> getCustomersBalance();

    Students addStudents(Students students);
    Integer addListStudents(List<Students> students);
}
