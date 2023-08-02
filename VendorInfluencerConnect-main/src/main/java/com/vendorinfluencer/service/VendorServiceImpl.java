package com.vendorinfluencer.service;

import com.vendorinfluencer.entity.Vendor;
import com.vendorinfluencer.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorServiceImpl implements VendorService{

	@Autowired
	VendorRepository vendorRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public void registerUser(Vendor vendor) {
		vendorRepo.save(vendor);
	}

	public Vendor checkIfPresent(String email) {
		return vendorRepo.findByEmail(email);
	}

	public Vendor findVendorById(int id){
		return vendorRepo.findByVendorId(id);
	}


	public void updateVendor(String firstname, String lastname, String password,int vendorId) {
		String hashedPassword  = passwordEncoder.encode(password);
		vendorRepo.updateVendorByVendorId(firstname,lastname,hashedPassword,vendorId);
	}


	public List<Vendor> getAllVendors(){
		return vendorRepo.findAll();
	}

}
