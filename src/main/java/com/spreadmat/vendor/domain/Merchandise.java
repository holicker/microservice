package com.spreadmat.vendor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString(exclude = {"vendor"})
@Table(name = "merchandise")
@Slf4j
public class Merchandise implements Serializable {

    @Id
    @Column(name = "merchandise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "merchandise_name")
    private String merchandiseName;

    @Column(name = "merchandise_description")
    private String merchandiseDescription;

    @Column(name = "merchandise_price")
    private int merchandisePrice;

    @Column(name = "registerd_date")
    private LocalDate registerdDate;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;

    @ManyToOne
    @JsonIgnoreProperties("merchandises")
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    public static Merchandise createMerchandise(Merchandise newMerchandise){
        log.info("=========== Domain : CreateMerchandise");
        Merchandise merchandise = new Merchandise()
                .merchandiseName(newMerchandise.getMerchandiseName())
                .merchandiseDescription(newMerchandise.getMerchandiseDescription())
                .merchandisePrice(newMerchandise.getMerchandisePrice())
                .registerdDate(LocalDate.now())
                .modifiedDate(null)
                .vendor(newMerchandise.getVendor());
        log.info("=========== Merchandise : " + merchandise);
        return merchandise;
    }

    public Merchandise merchandiseName(String merchandiseName){
        this.merchandiseName = merchandiseName;
        return this;
    }

    public Merchandise merchandiseDescription(String merchandiseDescription){
        this.merchandiseDescription = merchandiseDescription;
        return this;
    }

    public Merchandise merchandisePrice(int merchandisePrice){
        this.merchandisePrice = merchandisePrice;
        return this;
    }

    public Merchandise registerdDate(LocalDate registerdDate){
        this.registerdDate = registerdDate;
        return this;
    }

    public Merchandise modifiedDate(LocalDate modifiedDate){
        this.modifiedDate = modifiedDate;
        return this;
    }

    public Merchandise vendor(Vendor vendor){
        this.vendor = vendor;
        return this;
    }

}
