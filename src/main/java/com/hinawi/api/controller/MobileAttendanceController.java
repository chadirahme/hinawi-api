package com.hinawi.api.controller;


import com.hinawi.api.domains.MobileAttendance;
import com.hinawi.api.domains.WebDashboard;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.services.UserService;
import com.hinawi.api.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/attendance/")
public class MobileAttendanceController {

    private static final Logger logger = LoggerFactory.getLogger(MobileAttendanceController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value= "/addMobileAttendance", method= RequestMethod.POST)
    public ApiResponse<MobileAttendance> addMobileAttendance(@RequestBody MobileAttendance mobileAttendance) {
        try {
            userService.addMobileAttendance(mobileAttendance);
            return new ApiResponse<>(HttpStatus.OK.value(), "Attendance added successfully.", mobileAttendance);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= Constants.MOBILE_ATTENDANCE, method= RequestMethod.GET)
    public ApiResponse<List<MobileAttendance>> getMobileAttendace() {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "Mobile list fetched successfully.", userService.getMobileAttendance());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= "/checkIfUserCheckedIn", method= RequestMethod.GET)
    public ApiResponse<MobileAttendance> getMobileAttendace(@RequestParam("userId") int userId) {
        try {
            MobileAttendance mobileAttendance=null;
            List<MobileAttendance> lst= userService.checkIfUserCheckedIn(userId);
            if(lst!=null && lst.size()>0){
                mobileAttendance= lst.get(0);
            }
            return new ApiResponse<>(HttpStatus.OK.value(), "checkIfUserCheckedIn successfully.", mobileAttendance);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value= "/findLastUserVisit", method= RequestMethod.GET)
    public ApiResponse<MobileAttendance> findLastUserVisit(@RequestParam("userId") int userId) {
        try {
            MobileAttendance mobileAttendance=userService.findLastUserVisit(userId);;
            return new ApiResponse<>(HttpStatus.OK.value(), "findLastUserVisit successfully.", mobileAttendance);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
}
