package com.hinawi.api.services.impl;
import com.hinawi.api.domains.MobileAttendance;
import com.hinawi.api.domains.Users;
import com.hinawi.api.dto.ReportsModel;
import com.hinawi.api.repository.MobileAttendanceRepository;
import com.hinawi.api.repository.UsersRepository;
import com.hinawi.api.services.ReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by chadirahme on 2/24/20.
 */
@Service
public class ReportsServiceImpl implements ReportsService {

    private static final Logger logger = LoggerFactory.getLogger(ReportsServiceImpl.class);

    @Autowired
    MobileAttendanceRepository mobileAttendanceRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public List<ReportsModel> getAttendaceReport(Integer month) {
        List<ReportsModel> lst= mobileAttendanceRepository.getAttendanceReport(month);
        return lst;
    }

    @Override
    public List<ReportsModel> getMonthlyAttendaceReport(Integer month) {
        List<ReportsModel> lst= mobileAttendanceRepository.getMonthlyAttendanceReport(month);
        return lst;
    }

    @Override
    public List<Users> getUsersByStatusAndActivation(Integer status, Integer activation) {
        return usersRepository.findByActivation(activation).
                stream()
                .sorted(Comparator.comparing(Users::getUserName))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReportsModel> getAttendaceReportByReason(Integer userId, String start){
        if(start.equals("0"))
            return
                new ArrayList<ReportsModel>();

        try {

            logger.info("userId>>> " + userId);
            logger.info("start>>> " + start);

            List<ReportsModel> lst = mobileAttendanceRepository.getAttendanceReportByReason(userId, start);
            logger.info("getAttendanceReportByReason>>> " + lst.size());

            //get the checkout reason to add them to Reason report
            List<ReportsModel> lstMove = getAttendanceReportByMovement(userId, start);
            logger.info("getAttendanceReportByMovement>>> " + lstMove.size());

            Map<String, Integer> groupCheckOutReason = lstMove.stream()
                    .collect(Collectors.groupingBy(ReportsModel::getCheckoutReasonDesc, Collectors.summingInt(ReportsModel::getMoveHours)));

            Map<String, Integer> minutesCheckOutReason = lstMove.stream()
                    .collect(Collectors.groupingBy(ReportsModel::getCheckoutReasonDesc, Collectors.summingInt(ReportsModel::getMoveMinutes)));


            groupCheckOutReason.forEach((key, val) -> //System.out.println("Key : " + k + " Value : " + v));
            {
                ReportsModel reportsModel = new ReportsModel();
                reportsModel.setReasonDesc(key);
                reportsModel.setTotalHours(val);
                reportsModel.setTotalMinutes(minutesCheckOutReason.get(key));
                lst.add(reportsModel);
            });
            return lst;
        }
        catch (Exception ex)
        {
            logger.info(ex.getMessage());
            return
                    new ArrayList<ReportsModel>();

        }

//                for(ReportsModel item:lstMove){
//                    ReportsModel reportsModel=new ReportsModel();
//                    reportsModel.setReasonDesc(item.getCheckoutReasonDesc());
//                    reportsModel.setMonthlyDuration(item.getMoveDuration());
//                    //  reportsModel.getMoveDuration();
//                    reportsModel.setTotalHours(item.getMoveHours());
//                    reportsModel.setTotalMinutes(item.getMoveMinutes());
//                    lst.add(reportsModel);
//                }

    }

    @Override
    public List<ReportsModel> getAbsenceReport(String start, String end) {
        //get all active users to check if they absence
        List<ReportsModel> results=new ArrayList<ReportsModel>();
        List<Users> lstUsers= getUsersByStatusAndActivation(1,1);

        List<ReportsModel> lst= mobileAttendanceRepository.getAbsenceReport(start,end);
        // Get distinct objects by key
        List<ReportsModel> distinctDates = lst.stream()
                .filter( distinctByKey(p -> p.getCheckDate()))
                .collect( Collectors.toList());


        for (ReportsModel item:distinctDates){
            for(Users user:lstUsers){
                ReportsModel reportsModel=  lst.stream().filter(p->p.getUserId()==user.getUserId()
                && p.getCheckDate().equals(item.getCheckDate())).findAny().orElse(null);
                if(reportsModel==null){
                    results.add(new ReportsModel(Integer.parseInt(user.getUserId().toString()),user.getUserName(),item.getCheckDate()));
                }

            }
        }
        return results;
    }

    @Override
    public List<ReportsModel> getAttendanceReportByMovement(Integer userId, String start) {
        List<ReportsModel> results=new ArrayList<ReportsModel>();
        List<ReportsModel> lst= mobileAttendanceRepository.getAttendanceReportByMovement(userId,start);
        if(lst==null || lst.size()<2)
            return results;

        int index=0;
        ReportsModel item=lst.get(index);

        ReportsModel reportsModel=new ReportsModel();
        reportsModel.setFromCustomerName(item.getCustomerName());
        reportsModel.setCheckoutTime(item.getCheckoutTime());
        reportsModel.setCheckoutReasonDesc(item.getCheckoutReasonDesc());
        reportsModel.setCheckoutLatitude(item.getCheckoutLatitude());
        reportsModel.setCheckoutLongitude(item.getCheckoutLongitude());

        while (index++<lst.size()) {
            ReportsModel nextItem = getNextItem(lst, index);
            if (nextItem != null) {
                reportsModel.setToCustomerName(nextItem.getCustomerName());
                reportsModel.setCheckinTime(nextItem.getCheckinTime());
                reportsModel.setReasonDesc(nextItem.getReasonDesc());
                reportsModel.setVisitCheckinTime(nextItem.getCheckinTime());
                reportsModel.setVisitCheckoutTime(nextItem.getCheckoutTime());
                reportsModel.setCheckinLatitude(nextItem.getCheckinLatitude());
                reportsModel.setCheckinLongitude(nextItem.getCheckinLongitude());
                //call this to save moveHours and moveMinutes
                reportsModel.getMoveDuration();
                results.add(reportsModel);
                //add the check in again
                reportsModel = new ReportsModel();
                reportsModel.setFromCustomerName(nextItem.getCustomerName());
                reportsModel.setCheckoutTime(nextItem.getCheckoutTime());
                reportsModel.setCheckoutReasonDesc(nextItem.getCheckoutReasonDesc());
                reportsModel.setCheckoutLatitude(item.getCheckoutLatitude());
                reportsModel.setCheckoutLongitude(item.getCheckoutLongitude());
            }

        }
//        ReportsModel nextItem=getNextItem(lst,index+1);
//        if(nextItem!=null){
//            reportsModel.setToCustomerName(nextItem.getCustomerName());
//            reportsModel.setCheckinTime(nextItem.getCheckinTime());
//            reportsModel.setReasonDesc(nextItem.getReasonDesc());
//            results.add(reportsModel);
//        }
//        index++;

//        while (index<lst.size()){
//            index++;
//            reportsModel=new ReportsModel();
//            reportsModel.setFromCustomerName(nextItem.getCustomerName());
//            reportsModel.setCheckoutTime(nextItem.getCheckoutTime());
//            reportsModel.setCheckoutReasonDesc(nextItem.getCheckoutReasonDesc());
//            ReportsModel nextItem2=getNextItem(lst,index);
//            if(nextItem2!=null){
//                reportsModel.setToCustomerName(nextItem2.getCustomerName());
//                reportsModel.setCheckinTime(nextItem2.getCheckinTime());
//                reportsModel.setReasonDesc(nextItem2.getReasonDesc());
//                results.add(reportsModel);
//            }
//
//        }

        return results;
    }

    private ReportsModel getNextItem(List<ReportsModel> lst , int index){
        if(index >= lst.size()){
            //index not exists
            return null;
        }else{
            // index exists
            return lst.get(index);
        }
    }

    //Utility function
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
