package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerCheckmast extends Checkmast {

    @Column(name="Status")
    private String status;
}
