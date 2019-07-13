package com.hinawi.api.domains;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Web_Messages")
@Getter
@Setter
@NoArgsConstructor
public class WebMessages {

    @Id
    @Column(name="MessageId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;

    @Column(name="Message")
    private String message;

    @Column(name="UserId")
    private Integer userId;

    @Column(name="UserName")
    private String userName;

    @Column(name="StatusId")
    private Integer statusId;

    @Column(name="MessageDate")
    private Date messageDate;
}
