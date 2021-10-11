package com.spreadmat.vendor.domain;

import com.spreadmat.vendor.domain.enumeration.VendorState;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@ToString(exclude = {"merchandises"})
@NoArgsConstructor
@Slf4j
public class Vendor implements Serializable {

    // vendor 일련번호
    @Id
    @Column(name = "vendor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 일련번호
    @Column(name = "user_id")
    private Long userId;

    // 오픈 상태
    @Column(name = "open_state")
    private VendorState vendorState;

    // 판매 아이템 목록
    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Merchandise> merchandises = new HashSet<>();

    // 최초로 벤더 생성
    public static Vendor createVendor(Long userId) {
        Vendor vendor = new Vendor();
        vendor.setUserId(userId);
        vendor.setVendorState(VendorState.CLOSED);
        return vendor;
    }

    // 벤더 상품 추가하기
    public Vendor addMerchandise(Merchandise merchandise){
        this.merchandises.add(merchandise);
        log.info("=========== AddMerchandise : Complete");
        return this;
    }

    // 벤더 상품 등록하기
    public Vendor registerMerchandise(Merchandise newMerchandise){
        log.info("=========== Domain : RegisterMerchandise");
        this.addMerchandise(Merchandise.createMerchandise(newMerchandise));
        return this;
    }

    // 벤더 상품 수정하기
    public Vendor modifyMerchandise(Merchandise newMerchandise){
        log.info("=========== Vendor Domain Modify : " + newMerchandise);
        Stream<Merchandise> stream = this.merchandises.stream();
        stream.filter(merchandise -> merchandise.getId() == newMerchandise.getId())
                .forEach(merchandise -> merchandise
                        .merchandiseName(newMerchandise.getMerchandiseName())
                        .merchandiseDescription(newMerchandise.getMerchandiseDescription())
                        .merchandisePrice(newMerchandise.getMerchandisePrice())
                        .modifiedDate(LocalDate.now()));
        log.info("=========== Vendor Domain Complele");
        return this;
    }

    // 벤더 상품 삭제하기
    public Vendor deleteMerchandise(Long merchandiseId){
        log.info("=========== Delete " + merchandiseId);
        Set<Merchandise> results = new HashSet<>();
        Stream<Merchandise> stream = this.merchandises.stream();
        Merchandise targetMerchandise = stream.filter(merchandise -> merchandise.getId().equals(merchandiseId)).findFirst().get();
        this.merchandises.remove(targetMerchandise);
        log.info("=========== Domain Delete Complete");
        return this;
    }

    // 벤더 개점/폐쇄/휴식으로 상태 변경하기
    public Vendor changeVendorState(int vendorState){
        this.vendorState = (vendorState == 0) ?  VendorState.CLOSED : (vendorState == 1) ? VendorState.READY : VendorState.OPEN;
        return this;
    }

    // 벤터 초기화하기
    public Vendor resetVendor(){
        this.setVendorState(VendorState.CLOSED);
        this.merchandises = new HashSet<>();
        return this;
    }
}
