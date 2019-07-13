package com.hinawi.api.utils;

/**
 * Created by chadirahme on 12/19/18.
 */
public interface Constants {
    static final String GET_USER_BY_ID = "/getUser/{userId}";
    static final String GET_ALL_USERS = "/getAllUsers";
    static final String SAVE_USER = "/saveUser";
    static final String LOGIN_USER = "/loginUser";
    static final String CUSTOMERS_LIST = "/customersList";
    static final String PROSPECTIVE_LIST = "/prospectiveList";
    static final String VENDORS_LIST = "/vendorsList";
    static final String VENDORS_BALANCE = "/vendorsBalance";
    static final String CUSTOMERS_BALANCE = "/customersBalance";

    static final String STUDENTS_LIST = "/studentsList";
    static final String ADD_RANDOM_STUDENTS = "/addRandomStudents";

    static final String MESSAGES_LIST = "/messagesList";
    static final String ADD_MESSAGE = "/addMessage";

    static final String ADD_WEBDASHBOARD = "/addWebDashBoard";
    static final String DELETE_WEBDASHBOARD = "/deleteWebDashBoard";
    static final String GET_USERWEBDASHBOARD = "/getUserDashboards";

    static final String MOBILE_ATTENDANCE = "/mobileAttendance";

    //"v=spf1 mx -all"
}
