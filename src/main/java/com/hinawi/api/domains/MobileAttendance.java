package com.hinawi.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hinawi.api.converter.LocalDateTimeAttributeConverter;
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

    //@Temporal(TemporalType.DATE)
    //@Convert(converter = LocalDateTimeAttributeConverter.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name="CheckinTime")
    private LocalDateTime checkinTime;

    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    //@Column(name="CheckinTime")
    @Transient
    private Date localCheckinTime;

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

}
