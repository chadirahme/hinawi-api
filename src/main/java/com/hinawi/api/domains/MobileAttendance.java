package com.hinawi.api.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hinawi.api.converter.LocalDateTimeAttributeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

}
