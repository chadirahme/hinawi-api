package com.hinawi.api.controller;

import com.hinawi.api.domains.MobileAttendance;
import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.dto.ReportsModel;
import com.hinawi.api.services.ReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chadirahme on 2/24/20.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/reports/")
public class ReportsController {

    private static final Logger logger = LoggerFactory.getLogger(ReportsController.class);

    @Autowired
    ReportsService reportsService;

    @GetMapping(value = "/attendanceDaily")
    public ApiResponse<List<ReportsModel>> getMobileAttendanceDaily(@RequestParam("month") Integer month) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "MobileAttendance list fetched successfully.",
                    reportsService.getAttendaceReport(month));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/attendanceMonthly")
    public ApiResponse<List<ReportsModel>> getMonthlyAttendaceReport(@RequestParam("month") Integer month) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "MobileAttendance list fetched successfully.",
                    reportsService.getMonthlyAttendaceReport(month));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/activeUsers")
    public ApiResponse<List<Users>> getActiveUsers() {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "ActiveUsers list fetched successfully.",
                    reportsService.getUsersByStatusAndActivation(1,1));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/attendanceByReason")
    public ApiResponse<List<ReportsModel>> getAttendaceReportByReason(@RequestParam("month") Integer month,
                                                                      @RequestParam("userId") Integer userId,
                                                                      @RequestParam("start") String start) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "getAttendaceReportByReason list fetched successfully.",
                    reportsService.getAttendaceReportByReason(userId,start));
        }
        catch (Exception ex)
        {
            logger.info(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }


    @GetMapping(value = "/getAbsenceReport")
    public ApiResponse<List<ReportsModel>> getAbsenceReport(@RequestParam("start") String start, @RequestParam("end") String end) {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "getAbsenceReport list fetched successfully.",
                    reportsService.getAbsenceReport(start,end));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
    @GetMapping(value = "/attendanceByMovement")
    public ApiResponse<List<ReportsModel>> getAttendanceReportByMovement(@RequestParam("userId") Integer userId,@RequestParam("start") String start,
                                                                         @RequestParam("end") String end) {
        try {

            return new ApiResponse<>(HttpStatus.OK.value(), "getAttendanceReportByMovement list fetched successfully.",
                    reportsService.getAttendanceReportByMovement(userId, start));
        }
        catch (Exception ex)
        {
            logger.info(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
}
