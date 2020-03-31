package com.hinawi.api.services;

import com.hinawi.api.domains.MobileAttendance;
import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.ReportsModel;

import java.util.List;

/**
 * Created by chadirahme on 2/24/20.
 */
public interface ReportsService {

    List<ReportsModel> getAttendaceReport(Integer month);

    List<ReportsModel> getMonthlyAttendaceReport(Integer month);

    //List<ReportsModel> getAttendaceReportByReason(Integer month,Integer userId);
    List<ReportsModel> getAttendaceReportByReason(Integer userId, String start);

    List<Users> getUsersByStatusAndActivation(Integer status, Integer activation);

    //List<Users> getUsersByActivation(Integer activation);

    List<ReportsModel> getAbsenceReport(String start,  String end);

    List<ReportsModel> getAttendanceReportByMovement(Integer userId, String start);
}
