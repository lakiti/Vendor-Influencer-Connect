package com.vendorinfluencer.controller;

import com.vendorinfluencer.dto.Message;
import com.vendorinfluencer.entity.Vendor;
import com.vendorinfluencer.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class AdminController {

	@Autowired
	VendorService vService;

	@GetMapping("/getAllVendors")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Vendor> listAllVendors() {
		List<Vendor> v = vService.getAllVendors();

		for(int i=0;i<v.size();i++)
		{
			if(v.get(i).getRole().equals("ROLE_ADMIN"))
				v.remove(i);
		}
		return v;
	}

	@PostMapping("/blockVendor")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Message blockVendor(@RequestBody Vendor v) {
		Vendor vendor = vService.findVendorById(v.getVendorId());
		vendor.setIsBlocked(1);
		vService.registerUser(vendor);
		return new Message(vendor.getFirstname()+" "+vendor.getLastname()+ " blocked");
	}

	@PostMapping("/unblockVendor")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Message unblockVendor(@RequestBody Vendor v) {
		Vendor vendor = vService.findVendorById(v.getVendorId());
		vendor.setIsBlocked(0);
		vService.registerUser(vendor);
		return new Message("Unblocked " + vendor.getFirstname()+" "+vendor.getLastname());
	}
}