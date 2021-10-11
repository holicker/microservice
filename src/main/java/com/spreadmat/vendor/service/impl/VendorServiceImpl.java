package com.spreadmat.vendor.service.impl;

import com.spreadmat.vendor.domain.Merchandise;
import com.spreadmat.vendor.domain.Vendor;
import com.spreadmat.vendor.repository.VendorRepository;
import com.spreadmat.vendor.service.VendorService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Data
@Slf4j
public class VendorServiceImpl implements VendorService {

    @Autowired
    private final VendorRepository vendorRepository;

    @Override
    public Vendor save(Vendor vendor){
        log.info("=========== Save Complete : vendor - " + vendor);
        return vendorRepository.save(vendor);
    }

    @Override
    public Page<Vendor> findAll(Pageable pageable){
        return vendorRepository.findAll(pageable);
    }

    @Override
    public Optional<Vendor> findOne(Long id){
        return vendorRepository.findById(id);

    }

    @Override
    public void delete(Long vendorId){
        vendorRepository.deleteById(vendorId);
    }

    @Override
    public Vendor createVendor(Long userId){
        Vendor vendor = Vendor.createVendor(userId);
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor registerMerchandise(Long vendorId, Merchandise newMerchandise) {
        log.info("=========== Service : RegisterMerchandise");
        Vendor vendor = vendorRepository.findById(vendorId).get();
        Merchandise merchandise = newMerchandise.vendor(vendor);
        log.info("=========== Found : Vendor = " + vendor.toString());
        log.info("=========== Target : Merchandise = " + merchandise.toString());
        vendor.registerMerchandise(merchandise);
        log.info("=========== Vendor Registed Merchandise");
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor modifyMerchandise(Long vendorId, Merchandise newMerchandise) {
        log.info("=========== Service : 수정 시작");
        log.info("=========== New Merchandise : " + newMerchandise);
        Vendor vendor = vendorRepository.findById(vendorId).get();
        log.info("=========== Target Vendor : " + vendor);
        vendor.modifyMerchandise(newMerchandise);
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor deleteMerchandise(Long vendorId, Long merchandiseId) {
        log.info("=========== Service : 삭제 시작");
        log.info("=========== Vendor Id : " + vendorId);
        log.info("=========== Merchandise Id : " + merchandiseId);
        Vendor vendor = vendorRepository.findById(vendorId).get();
        log.info("=========== Vendor : " + vendor);
        vendor.deleteMerchandise(merchandiseId);
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor changeVendorState(Long vendorId, int vendorState) {
        Vendor vendor = vendorRepository.findById(vendorId).get();
        vendor.changeVendorState(vendorState);
        return vendorRepository.save(vendor);
    }
}
