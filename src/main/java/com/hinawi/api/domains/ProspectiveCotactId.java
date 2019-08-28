package com.hinawi.api.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class ProspectiveCotactId implements Serializable {

   // @Id
    //@Column(name="RecNo")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recNo;

   // @Id
    @Column(name="[LineNo]")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineNo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProspectiveCotactId that = (ProspectiveCotactId) o;

        if (!recNo.equals(that.recNo)) return false;
        return lineNo.equals(that.lineNo);
        //return recNo.equals(that.recNo);
    }

    @Override
    public int hashCode() {
        int result = recNo.hashCode();
        result = 31 * result+ lineNo.hashCode();
        return result;
    }
}
