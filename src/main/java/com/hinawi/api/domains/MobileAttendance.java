package com.hinawi.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hinawi.api.converter.LocalDateTimeAttributeConverter;
import com.hinawi.api.dto.ReportsModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "MobileAttendance")
@Getter
@Setter
@NoArgsConstructor
@SqlResultSetMapping(name = "MobileAttendance.dailyMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReportsModel.class,
                        columns = {
                                @ColumnResult(name = "userId"),
                                @ColumnResult(name = "userName"),
                                @ColumnResult(name = "checkinTime"),
                                @ColumnResult(name = "checkoutTime")
//                                @ColumnResult(name = "pattern_dispatcher_url", type = String.class),
//                                @ColumnResult(name = "parent_id", type = Long.class),
//                                @ColumnResult(name = "con", type = Integer.class)

                        })
        })
@SqlResultSetMapping(name = "MobileAttendance.monthlyMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReportsModel.class,
                        columns = {
                                @ColumnResult(name = "userId"),
                                @ColumnResult(name = "userName"),
                                @ColumnResult(name = "monthName"),
                                @ColumnResult(name = "totalHours"),
                                @ColumnResult(name = "totalMinutes"),
                                @ColumnResult(name = "ratePerHour")

                        })
        })

@SqlResultSetMapping(name = "MobileAttendance.employeeByReasonMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReportsModel.class,
                        columns = {
                                @ColumnResult(name = "monthName"),
                                @ColumnResult(name = "ReasonDesc"),
                                @ColumnResult(name = "totalHours"),
                                @ColumnResult(name = "totalMinutes")

                        })
        })

@SqlResultSetMapping(name = "MobileAttendance.absenceReportMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReportsModel.class,
                        columns = {
                                @ColumnResult(name = "UserId"),
                                @ColumnResult(name = "Username"),
                                @ColumnResult(name = "checkDate")
                        })
        })

@SqlResultSetMapping(name = "MobileAttendance.dailyByMovementMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReportsModel.class,
                        columns = {
                                @ColumnResult(name = "UserName"),
                                @ColumnResult(name = "CheckinTime"),
                                @ColumnResult(name = "CheckoutTime"),
                                @ColumnResult(name = "CheckinLatitude"),
                                @ColumnResult(name = "CheckinLongitude"),
                                @ColumnResult(name = "CheckoutLatitude"),
                                @ColumnResult(name = "CheckoutLongitude"),
                                @ColumnResult(name = "CheckinNote"),
                                @ColumnResult(name = "CheckoutNote"),
                                @ColumnResult(name = "CustomerType"),
                                @ColumnResult(name = "CustomerName"),
                                @ColumnResult(name = "ReasonDesc"),
                                @ColumnResult(name = "CheckoutReasonDesc"),
                        })
        })

//@SqlResultSetMapping(name="MobileAttendance.carkey",
//        entities=@EntityResult(entityClass=MobileAttendance.class,
//                fields = {
//                        @FieldResult(name="userId", column = "userId"),
//                        @FieldResult(name="userName", column = "userName")
////                        @FieldResult(name="dimension.length", column = "length"),
////                        @FieldResult(name="dimension.width", column = "width")
//                })
        //columns = { @ColumnResult(name = "area")}
 //       )
@NamedNativeQuery(name="MobileAttendance.getAttendanceReport",
        query="select userId,userName,CONVERT(nvarchar,Min(checkinTime), 120) as checkinTime,"+
        " CONVERT(nvarchar,Max(checkoutTime), 120) as checkoutTime" +
                " FROM MobileAttendance p WHERE year(checkintime)=2020 and month(checkintime) = :month and month(checkouttime)= :month " +
                " and DATEADD(dd, DATEDIFF(dd, 0, checkinTime), 0)=DATEADD(dd, DATEDIFF(dd, 0, checkoutTime), 0)" +
                " group by userid,username,DATEADD(dd, DATEDIFF(dd, 0, checkinTime), 0)" +
                " ",
        resultSetMapping="MobileAttendance.dailyMapping")

@NamedNativeQuery(name="MobileAttendance.getMonthlyAttendanceReport",
        query= " select t.userid, t.username, t.monthName, SUM(t.hours) as 'totalHours', sum(t.minutes) as 'totalMinutes',min(RatePerHour) as 'ratePerHour' " +
                " from(" +
                " select m.userid,m.username, datename(M,checkoutTime) as 'monthName'," +
                "         DATEPART(Hour, checkoutTime - checkinTime) as 'hours', " +
                "         DATEPART(Minute, checkoutTime - checkinTime) as 'minutes', e.RatePerHour " +
                " from MobileAttendance m " +
                "                 left join Users u on m.UserId=u.Userid " +
                "                 left join EmpMast e on e.Emp_key=u.Emp_key " +
                " where year(checkintime)=2020 and month(checkintime)= :month and month(checkouttime)= :month" +
                " and DATEADD(dd, DATEDIFF(dd, 0, checkinTime), 0)=DATEADD(dd, DATEDIFF(dd, 0, checkoutTime), 0)" +
                " )t" +
                " group by userid,username,monthName" +
                " ",
        resultSetMapping="MobileAttendance.monthlyMapping")

@NamedNativeQuery(name="MobileAttendance.getAttendanceReportByReason",
        query= " select Min(datename(M,checkoutTime)) as 'monthName', ReasonDesc, " +
                " Sum(DATEPART(Hour, checkoutTime - checkinTime)) as 'totalHours',"+
                " sum(DATEPART(Minute, checkoutTime - checkinTime)) as 'totalMinutes'"+
                " from mobileattendance"+
                " where year(checkintime)=2020 and userid= :userId"+
                " and  month(checkintime)= :month and month(checkouttime)= :month"+
                " and ReasonDesc is not null"+
                " group by ReasonDesc",
        resultSetMapping="MobileAttendance.employeeByReasonMapping")

//select UserId,Username,DATEADD(dd, 0, DATEDIFF(dd, 0,checkintime))
//        from MobileAttendance
//        group by userid,username,DATEADD(dd, 0, DATEDIFF(dd, 0,checkintime))
//        order by 3

@NamedNativeQuery(name="MobileAttendance.getAbsenceReport",
        query= " select UserId,Username,CONVERT(varchar, DATEADD(dd, 0, DATEDIFF(dd, 0,checkintime)), 23) as 'checkDate' " +
                " from mobileattendance"+
                " where checkintime between :start and :end"+
                " group by userid,username,DATEADD(dd, 0, DATEDIFF(dd, 0,checkintime))"+
                " order by 3",
        resultSetMapping="MobileAttendance.absenceReportMapping")


@NamedNativeQuery(name="MobileAttendance.getAttendanceReportByMovement",
        query= " select UserName,CONVERT(nvarchar,(checkinTime), 120) as 'CheckinTime',CONVERT(nvarchar,(CheckoutTime), 120) as 'CheckoutTime',CheckinLatitude,CheckinLongitude, "+
                " CheckoutLatitude,CheckoutLongitude,CheckinNote,CheckoutNote,CustomerType,CustomerName," +
                " ReasonDesc,CheckoutReasonDesc " +
                " from MobileAttendance where userid= :userId and " +
                " DATEADD(dd, DATEDIFF(dd, 0, checkinTime), 0)= :start"+
                " order by checkinTime,AttendId"
        ,
        resultSetMapping="MobileAttendance.dailyByMovementMapping")

//select Min(month(checkintime)) as 'monthName', ReasonDesc,
//        Sum(DATEPART(Hour, checkoutTime - checkinTime)) as 'hours',
//        sum(DATEPART(Minute, checkoutTime - checkinTime)) as 'minutes'
//
//        from mobileattendance
//        where userid='30'
//        and  month(checkintime)=2 and month(checkouttime)=2
//        and ReasonDesc is not null
//        group by ReasonDesc


//https://www.concretepage.com/hibernate/native_query_hibernate_annotation
public class MobileAttendance {

//    select w.AttendId,w.UserId,w.CheckinTime,w.CustomerName,w.CustomerType,
//    w.CheckinTime,w.CheckinLatitude,w.CheckinLongitude,
//    w.CheckoutTime,w.CheckoutLatitude,w.CheckoutLongitude,
//    w.Reason,w.Result,w.CheckinNote,w.CheckoutNote
//    from MobileAttendance w

    @Id
    @Column(name="AttendId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendId;

    @Column(name="UserId")
    private int userId;

    @Column(name="UserName")
    private String userName;

    @Column(name="CustomerName")
    private String customerName;

    @Column(name="CustomerType")
    private String customerType;

    @Column(name="CheckinNote")
    private String checkinNote;

    @Column(name="CheckoutNote")
    private String checkoutNote;

    @Column(name="ReasonDesc")
    private String reasonDesc;

    @Column(name="ReasonId")
    private int reasonId;

    @Column(name="CheckoutReasonDesc")
    private String checkoutReasonDesc;

    @Column(name="CheckoutReasonId")
    private int checkoutReasonId;

    //@Temporal(TemporalType.DATE)
    //@Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="CheckinTime")
    private LocalDateTime checkinTime;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //@Column(name="CheckinTime")
    @Transient
    private String localCheckinTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="CheckoutTime")
    private LocalDateTime checkoutTime;

    @Column(name="CheckinLatitude")
    private Float checkinLatitude;

    @Column(name="CheckinLongitude")
    private Float checkinLongitude;

    @Column(name="CheckoutLatitude")
    private Float checkoutLatitude;

    @Column(name="CheckoutLongitude")
    private Float checkoutLongitude;

    @Transient
    private String visitDuration;
    public String getVisitDuration(){
        if(checkoutTime==null)
            return "";

        LocalDateTime tempDateTime = LocalDateTime.from( checkinTime );
//        long years = tempDateTime.until( checkoutTime, ChronoUnit.YEARS );
//        tempDateTime = tempDateTime.plusYears( years );
//
//        long months = tempDateTime.until( checkoutTime, ChronoUnit.MONTHS );
//        tempDateTime = tempDateTime.plusMonths( months );
//
//        long days = tempDateTime.until( checkoutTime, ChronoUnit.DAYS );
//        tempDateTime = tempDateTime.plusDays( days );

        long hours = tempDateTime.until( checkoutTime, ChronoUnit.HOURS );
        tempDateTime = tempDateTime.plusHours( hours );

        long minutes = tempDateTime.until( checkoutTime, ChronoUnit.MINUTES );
        //tempDateTime = tempDateTime.plusMinutes( minutes );

        //long seconds = tempDateTime.until( checkoutTime, ChronoUnit.SECONDS );

        return  hours + " hours " +
                minutes + " minutes " ;
    }

    //https://www.geeksforgeeks.org/program-distance-two-points-earth/
    @Transient
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

}
