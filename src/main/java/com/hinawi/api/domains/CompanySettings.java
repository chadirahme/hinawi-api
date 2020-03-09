package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by chadirahme on 2/23/20.
 *
 */
@Entity
@Table(name = "compsetup")
@Getter
@Setter
@NoArgsConstructor
public class CompanySettings {

    @Id
    @Column(name="COMP_KEY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Comp_Name")
    private String companyName;
}
