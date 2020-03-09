package com.hinawi.api.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by chadirahme on 2/24/20.
 */
@Getter
@Setter
public class ReportsModel {

    private int userId;
    private String userName;
    private String checkinTime;
    private String checkoutTime;
    private String monthName;
    private int totalHours;
    private int totalMinutes;
    private String reasonDesc;
    private String checkDate;
    private Double ratePerHour;

    private Double checkinLatitude;
    private Double checkinLongitude;
    private Double checkoutLatitude;
    private Double checkoutLongitude;
    private String checkinNote;
    private String checkoutNote;
    private String customerType;
    private String customerName;
    private String checkoutReasonDesc;

    private String fromCustomerName;
    private String toCustomerName;

    private String visitCheckinTime;
    private String visitCheckoutTime;

    public ReportsModel(){

    }
    public ReportsModel(int userId,String userName,String checkinTime,String checkoutTime){
        this.userId=userId;
        this.userName=userName;
        this.checkinTime=checkinTime;
        this.checkoutTime=checkoutTime;
    }

    //monthlyMapping
    public ReportsModel(int userId,String userName,String monthName,int totalHours,int totalMinutes,Double ratePerHour){
        this.userId=userId;
        this.userName=userName;
        this.monthName=monthName;
        this.totalHours=totalHours;
        this.totalMinutes=totalMinutes;
        this.ratePerHour=ratePerHour;
    }

    public ReportsModel(String monthName,String reasonDesc, int totalHours,int totalMinutes){
        this.monthName=monthName;
        this.reasonDesc=reasonDesc;
        this.totalHours=totalHours;
        this.totalMinutes=totalMinutes;
    }


    public ReportsModel(int userId,String userName,String checkDate){
        this.userId=userId;
        this.userName=userName;
        this.checkDate=checkDate;
    }

    //dailyByMovementMapping
    public ReportsModel(String userName,String checkinTime,String checkoutTime,Double checkinLatitude,Double checkinLongitude,
                        Double checkoutLatitude,Double checkoutLongitude,String checkinNote, String checkoutNote,
                        String customerType,String customerName,String reasonDesc, String checkoutReasonDesc){

        this.userName=userName;
        this.checkinTime=checkinTime;
        this.checkoutTime=checkoutTime;
        this.checkinLatitude=checkinLatitude;
        this.checkinLongitude=checkinLongitude;
        this.checkoutLatitude=checkoutLatitude;
        this.checkoutLongitude=checkoutLongitude;
        this.checkinNote=checkinNote;
        this.checkoutNote=checkoutNote;
        this.customerType=customerType;
        this.customerName=customerName;
        this.reasonDesc=reasonDesc;
        this.checkoutReasonDesc=checkoutReasonDesc;
    }

    private String visitDuration;
    public String getVisitDuration(){
        if(checkoutTime==null || checkinTime==null)
            return "";

        LocalDateTime checkinDateTime = LocalDateTime.parse(checkinTime.replace(" ","T"));
        LocalDateTime checkoutDateTime = LocalDateTime.parse(checkoutTime.replace(" ","T"));

        LocalDateTime tempDateTime = LocalDateTime.from(checkinDateTime);
//        long years = tempDateTime.until( checkoutTime, ChronoUnit.YEARS );
//        tempDateTime = tempDateTime.plusYears( years );
//
//        long months = tempDateTime.until( checkoutTime, ChronoUnit.MONTHS );
//        tempDateTime = tempDateTime.plusMonths( months );
//
//        long days = tempDateTime.until( checkoutTime, ChronoUnit.DAYS );
//        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( checkoutDateTime, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( checkoutDateTime, ChronoUnit.MINUTES );
        //tempDateTime = tempDateTime.plusMinutes( minutes );

        //long seconds = tempDateTime.until( checkoutTime, ChronoUnit.SECONDS );

        return  hours + " Hours " +
                minutes + " Minutes " ;
    }

    public String getMoveDuration(){
        if(checkoutTime==null || checkinTime==null)
            return "";

        LocalDateTime checkinDateTime = LocalDateTime.parse(checkinTime.replace(" ","T"));
        LocalDateTime checkoutDateTime = LocalDateTime.parse(checkoutTime.replace(" ","T"));

        LocalDateTime tempDateTime = LocalDateTime.from(checkoutDateTime);

        long hours = tempDateTime.until( checkinDateTime, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( checkinDateTime, ChronoUnit.MINUTES );
        return  hours + " Hours " +
                minutes + " Minutes " ;
    }

    public String getReportVisitDuration(){
        if(visitCheckinTime==null || visitCheckoutTime==null)
            return "";

        LocalDateTime checkinDateTime = LocalDateTime.parse(visitCheckinTime.replace(" ","T"));
        LocalDateTime checkoutDateTime = LocalDateTime.parse(visitCheckoutTime.replace(" ","T"));

        LocalDateTime tempDateTime = LocalDateTime.from(checkinDateTime);
//        long years = tempDateTime.until( checkoutTime, ChronoUnit.YEARS );
//        tempDateTime = tempDateTime.plusYears( years );
//
//        long months = tempDateTime.until( checkoutTime, ChronoUnit.MONTHS );
//        tempDateTime = tempDateTime.plusMonths( months );
//
//        long days = tempDateTime.until( checkoutTime, ChronoUnit.DAYS );
//        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( checkoutDateTime, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( checkoutDateTime, ChronoUnit.MINUTES );
        //tempDateTime = tempDateTime.plusMinutes( minutes );

        //long seconds = tempDateTime.until( checkoutTime, ChronoUnit.SECONDS );

        return  hours + " Hours " +
                minutes + " Minutes " ;
    }

    private String monthlyDuration;
    public String getMonthlyDuration(){

        int hours = totalMinutes / 60; //since both are ints, you get an int
        int minutes = totalMinutes % 60;

        hours+=totalHours;
        monthlyDuration=String.format("%d Hours %02d Minutes", hours, minutes);
        return monthlyDuration;
    }

    private double monthlyRate;
    public double getMonthlyRate(){

        int hours = totalMinutes / 60; //since both are ints, you get an int
        int minutes = totalMinutes % 60;

        hours+=totalHours;
        if(ratePerHour!=null)
        return ratePerHour*hours;
        else
            return 0;
    }

    private String visitDistance;
    public String getVisitDistance() {
        if (checkoutLatitude == null || checkoutLongitude==null || checkinLatitude==null || checkinLongitude==null)
            return "";

        double lon1 = Math.toRadians(checkinLongitude);
        double lon2 = Math.toRadians(checkoutLongitude);
        double lat1 = Math.toRadians(checkinLatitude);
        double lat2 = Math.toRadians(checkoutLatitude);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        double result = (c * r);

        visitDistance = Math.round(result) + " K.M";
        return visitDistance;
    }

//    int getUserId();
//    String getUserName();
//    String getCheckinTime();
//    String getCheckoutTime();
//    String getVisitDuration();

//    Double getTotal();
//    int getPaymonth();
//    String getStatus();
}
