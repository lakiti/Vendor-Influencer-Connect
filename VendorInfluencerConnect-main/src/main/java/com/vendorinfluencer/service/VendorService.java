package com.vendorinfluencer.service;

import com.vendorinfluencer.entity.Vendor;

import java.util.List;

public interface VendorService {

    void registerUser(Vendor vendor);

    Vendor checkIfPresent(String email);

    Vendor findVendorById(int id);
    void updateVendor(String firstname, String lastname, String password, int vendorId);

    List<Vendor> getAllVendors();
}
