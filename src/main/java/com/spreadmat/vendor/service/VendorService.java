package com.spreadmat.vendor.service;

import com.spreadmat.vendor.domain.Merchandise;
import com.spreadmat.vendor.domain.Vendor;
import com.spreadmat.vendor.domain.event.UserIdCreated;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface VendorService {

    Vendor save(Vendor vendorDTO);

    Page<Vendor> findAll(Pageable pageable);

    Optional<Vendor> findOne(Long id);

    void delete(Long vendorId);

    // 벤더 초기 설립하기
    Vendor createVendor(Long userId);

    // 벤더 물품 등록
    Vendor registerMerchandise(Long vendorId, Merchandise newMerchandise);


    // 벤더 물품 수정
    Vendor modifyMerchandise(Long vendorId, Merchandise newMerchandise);


    // 벤더 물품 삭제
    Vendor deleteMerchandise(Long vendorId, Long merchandiseId);


    // 벤더 상태 변경
    Vendor changeVendorState(Long vendorId, int vendorState);


}
