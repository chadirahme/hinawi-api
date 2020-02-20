package com.hinawi.api.controller;

import com.hinawi.api.converter.UserConverter;
import com.hinawi.api.domains.*;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.dto.UserDto;
import com.hinawi.api.services.UserService;
import com.hinawi.api.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value= Constants.LOGIN_USER, method= RequestMethod.POST)
    public ApiResponse<Users> loginUser(@RequestBody Users users) {
        // UserDto userDto=UserConverter.entityToDto(users);
        try {
            Users dbUser = userService.loginUsers(users);
            if (dbUser == null) {
                return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "User not found !!.", dbUser);
            } else if (dbUser.getUserId() > 0)
                return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", dbUser,true);
            else
                return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "User not found !!.", dbUser);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.CUSTOMERS_LIST, method= RequestMethod.GET)
    public ApiResponse<List<Customers>> getCustomers() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Customers list fetched successfully.", userService.getCustomers());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
    @RequestMapping(value= Constants.PROSPECTIVE_LIST, method= RequestMethod.GET)
    public ApiResponse<List<Prospective>> getProspectives() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Prospective list fetched successfully.", userService.getProspectives());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.PROSPECTIVE_SORTED_LIST, method= RequestMethod.GET)
    public ApiResponse<List<Prospective>> getProspectivesSortedList() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Prospective list fetched successfully.", userService.getSortedProspectives());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }



    @RequestMapping(value= Constants.PROSPECTIVE_CONTACTS_LIST, method= RequestMethod.GET)
    public ApiResponse<List<ProspectiveCotact>> getProspectiveContacts(@RequestParam("recNo") long recNo) {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Prospective list fetched successfully.", userService.getProspectiveContacts(recNo));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= "/saveProspectives", method= RequestMethod.POST)
    public ApiResponse<Prospective> saveProspectives(@RequestBody Prospective prospective) {
        //try {
            userService.saveProspectives(prospective);
            return new ApiResponse<>(HttpStatus.OK.value(), "Prospective saved successfully.", prospective);
        //}
//        catch (Exception ex)
//        {
//            logger.error(ex.getMessage());
//            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
//        }
    }

    @RequestMapping(value= "/saveProspectiveContact", method= RequestMethod.POST)
    public ApiResponse<ProspectiveCotact> saveProspectives(@RequestBody ProspectiveCotact prospectiveCotact) {
        try {
            userService.saveProspectiveContacts(prospectiveCotact);
            return new ApiResponse<>(HttpStatus.OK.value(), "Prospective saved successfully.", prospectiveCotact);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }


    @RequestMapping(value= Constants.VENDORS_LIST, method= RequestMethod.GET)
    public ApiResponse<List<Vendors>> getVendors() {
        try {

            //return new ApiResponse<>(HttpStatus.OK.value(), "Vendors list fetched successfully.", userService.getVendors());
            return new ApiResponse<>(HttpStatus.OK.value(), "Vendors list fetched successfully.", userService.getSortedVendors());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }




    @RequestMapping(value= Constants.VENDORS_BALANCE, method= RequestMethod.GET)
    public ApiResponse<List<Vendors>> getVendorsBalance() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Vendors balance fetched successfully.", userService.getVendorsBalance());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.CUSTOMERS_BALANCE, method= RequestMethod.GET)
    public ApiResponse<List<Customers>> getCustomersBalance() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Customers balance fetched successfully.", userService.getCustomersBalance());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.STUDENTS_LIST, method= RequestMethod.GET)
    public ApiResponse<List<Students>> getStudentsList() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Students List fetched successfully.", userService.getStudents());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.ADD_RANDOM_STUDENTS, method= RequestMethod.GET)
    public ApiResponse<Integer> addRandomStudents() {
        try {

            String[] peoples = {"Amjad","Dawoud","Faraj","Brandon","Bashir","Bakir","Mustafa","Mohammad","Ahmad","Jamil","Adel","Abed","Halim","Ramiz","Wael"};
            List<String> names = Arrays.asList(peoples);
            //Collections.shuffle(names);
            Random rand = new Random();
            List<Students> lstStudents=new ArrayList<>();
            for(int i=0;i<20;i++) {
                Collections.shuffle(names);
                Students students = new Students();
                students.setEnrollNo(rand.nextInt(2000) + 1);
                students.setAcademicYear("2018-2019");
                students.setSchoolID(1);
                students.setProgramID(2);
                students.setGradeID(rand.nextInt(12) + 1);
                students.setSectionID(2);
                students.setGenderID(1);
                students.setEnFirstName(names.get(0));
                students.setEnMiddleName(names.get(1));
                students.setEnLastName(names.get(2));
                lstStudents.add(students);
            }

            userService.addListStudents(lstStudents);
            return new ApiResponse<>(HttpStatus.OK.value(), "Students addedd successfully.",lstStudents.size()); //userService.addStudents(students));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
    final java.util.Random rand = new java.util.Random();

    private String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
        }
        return builder.toString();
    }


    @RequestMapping(value= Constants.MESSAGES_LIST, method= RequestMethod.GET)
    public ApiResponse<List<WebMessages>> getMessagesList() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "messagesList fetched successfully.", userService.getWebMessages());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.GET_USERWEBDASHBOARD, method= RequestMethod.GET)
    public ApiResponse<List<WebMessages>> getUserDashboard(@RequestParam("userId") Integer userId) {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "dashbard fetched successfully.", userService.getUserDashboards(userId));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.ADD_WEBDASHBOARD, method= RequestMethod.POST)
    public ApiResponse<WebDashboard> addWebDashBoard(@RequestBody WebDashboard webDashboard) {
        // UserDto userDto=UserConverter.entityToDto(users);
        try {

            WebDashboard oldWebDashboard = userService.getWebDashboardByNameAndUser(webDashboard);//userService.getWebDashboardByName(webDashboard);
            if(oldWebDashboard!=null){
                oldWebDashboard.setDashOrder(webDashboard.getDashOrder());
                userService.addWebDashboard(oldWebDashboard);

            }else {
                WebDashboard dbWebDashboard = userService.addWebDashboard(webDashboard);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), "WebDashboard added successfully.", webDashboard);

        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.DELETE_WEBDASHBOARD, method= RequestMethod.POST)
    public ApiResponse<WebDashboard> deleteWebDashBoard(@RequestBody WebDashboard webDashboard) {
        try {

            WebDashboard dbWebDashboard = userService.deleteWebDashboard(webDashboard);
            return new ApiResponse<>(HttpStatus.OK.value(), "WebDashboard deleted successfully.", dbWebDashboard);

        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }


}
