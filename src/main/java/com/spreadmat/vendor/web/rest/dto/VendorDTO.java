package com.spreadmat.vendor.web.rest.dto;

import com.spreadmat.vendor.domain.enumeration.VendorState;
import lombok.Data;

import java.io.Serializable;

@Data
public class VendorDTO implements Serializable {
    private Long id;
    private Long userId;
    private VendorState vendorState;

}
