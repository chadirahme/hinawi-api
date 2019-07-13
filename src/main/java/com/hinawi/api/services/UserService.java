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
    List<MobileAttendance> getMobileAttendance();

    List<Vendors> getVendorsBalance();
    List<Customers> getCustomersBalance();

    List<Students> getStudents();
    Students addStudents(Students students);
    Integer addListStudents(List<Students> students);

    List<WebMessages> getWebMessages();
    WebMessages addWebMessages(WebMessages webMessages);

    List<WebDashboard> getUserDashboards(Integer userid);
    WebDashboard getWebDashboardByNameAndUser(WebDashboard webDashboard);
    WebDashboard getWebDashboardByName(WebDashboard webDashboard);
    WebDashboard addWebDashboard(WebDashboard webDashboard);
    WebDashboard deleteWebDashboard(WebDashboard webDashboard);

    MobileAttendance addMobileAttendance(MobileAttendance mobileAttendance);
}
