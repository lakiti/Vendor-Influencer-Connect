package com.vendorinfluencer.dto;

import com.vendorinfluencer.entity.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {

    String token;
    Vendor v;
}
