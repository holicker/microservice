package com.spreadmat.vendor.web.rest;

import com.spreadmat.vendor.config.CustomConfig;
import com.spreadmat.vendor.domain.Merchandise;
import com.spreadmat.vendor.service.VendorService;
import com.spreadmat.vendor.web.rest.dto.MerchandiseDTO;
import com.spreadmat.vendor.web.rest.dto.VendorDTO;
import com.spreadmat.vendor.web.rest.mapper.MerchandiseMapper;
import com.spreadmat.vendor.web.rest.mapper.VendorMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "vendor", produces = "application/json")
@Data
public class VendorController {

    @Autowired private final VendorService vendorService;
    @Autowired private final VendorMapper vendorMapper;
    @Autowired private final MerchandiseMapper merchandiseMapper;


    @GetMapping("/create/{userid}")
    public ResponseEntity<VendorDTO> createVendor(@PathVariable("userid") Long userId) {
        log.info("=========== userId : " + userId);
        VendorDTO result = vendorMapper.toDto(vendorService.createVendor(userId));
        log.info("=========== Save Complete");
        return new ResponseEntity<VendorDTO>(result, HttpStatus.CREATED);
    }

    @PostMapping("/register/{vendorid}")
    public ResponseEntity<Merchandise> registerMerchandise(@PathVariable("vendorid") Long vendorId, @RequestBody MerchandiseDTO merchandiseDTO){
        log.info("=========== Registering Merchandise Starting");
        log.info("=========== vendorId : " + vendorId);
        log.info("=========== merchandiseDTO : " + merchandiseDTO.toString());
        vendorService.registerMerchandise(vendorId, merchandiseMapper.toEntity(merchandiseDTO));
        return null;
    }

    @PostMapping("/modify/{vendorid}")
    public ResponseEntity<Merchandise> modifyMerchandise(@PathVariable("vendorid") Long vendorId, @RequestBody MerchandiseDTO merchandiseDTO){
        vendorService.modifyMerchandise(vendorId, merchandiseMapper.toEntity(merchandiseDTO));
        return null;
    }

    @GetMapping("/delete/{vendorid}/{merchandiseid}")
    public ResponseEntity<Merchandise> deleteMerchandise(@PathVariable("vendorid") Long vendorId, @PathVariable("merchandiseid") Long merchandiseId){
        vendorService.deleteMerchandise(vendorId, merchandiseId);
        return null;
    }

    @GetMapping("/change/{vendorid}/state/{state}")
    public ResponseEntity<VendorDTO> changeVendorState(@PathVariable("vendorid") Long vendorId, @PathVariable("state") int vendorState){
        vendorService.changeVendorState(vendorId, vendorState);
        return null;
    }
}
