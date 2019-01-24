package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Web_Dashboard")
@Getter
@Setter
@NoArgsConstructor
public class WebDashboard {

    @Id
    @Column(name="DashboardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dashboardId;

    @Column(name="DashName",unique = true)
    private String dashName;

    @Column(name="UserId")
    private Integer userId;

    @Column(name="DashOrder")
    private Integer dashOrder;
}
