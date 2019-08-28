package com.hinawi.api.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Vendor")
@Getter
@Setter
@NoArgsConstructor
public class VendorCheckmast extends Checkmast {

//    @Column(name="PayeeType")
//    private String payeeType;
}
