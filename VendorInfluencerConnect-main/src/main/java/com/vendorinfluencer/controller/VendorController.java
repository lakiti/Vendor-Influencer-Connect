package com.vendorinfluencer.controller;

import com.vendorinfluencer.dto.AuthRequest;
import com.vendorinfluencer.dto.Message;
import com.vendorinfluencer.dto.Token;
import com.vendorinfluencer.entity.Influencer;
import com.vendorinfluencer.entity.Vendor;
import com.vendorinfluencer.service.InfluencerService;
import com.vendorinfluencer.service.JwtService;
import com.vendorinfluencer.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VendorController {
	@Autowired
	InfluencerService influencerService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	VendorService vService;

	@Autowired
	JwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	String status ="";

	List<Influencer> influencerList = new ArrayList<>();
	List<Vendor> vendorList = new ArrayList<>();

	//	Registers vendor
	@PostMapping("/register")
	public Message registerUser(@RequestBody Vendor vendor) {
		String status;
		if (vService.checkIfPresent(vendor.getEmail()) != null) {

			status = "User already exists";
		} else {
			String hashedPassword = passwordEncoder.encode(vendor.getPassword());
			vendor.setPassword(hashedPassword);
			vService.registerUser(vendor);
			status = "registration successful";
		}

		return new Message(status);
	}


	//Will be used when vendor will apply filter to get influencers and add them to his list
	@PostMapping("/influencerByCategory")
	@PreAuthorize("hasAuthority('ROLE_VENDOR')")
	public List<Influencer> getInfluencers(@RequestBody Influencer influencer) {
		return influencerService.getInfluencers(influencer.getCategory());
	}

	@PostMapping("/authenticate")
	public Token authenticateAndGetToken(@RequestBody AuthRequest authReq) {
		Token response = null;
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
		Vendor v = vService.checkIfPresent(authReq.getUsername());
		if (authenticate.isAuthenticated()) {
			if(v.getIsBlocked()==0){
				response =  new Token(jwtService.generateToken(authReq.getUsername()),v);
			}else{
				response = new Token("You are blocked! Can't log you in...",null);
			}

		}
		return response;
	}

	@PostMapping("/updateVendor")
	@PreAuthorize("hasAuthority('ROLE_VENDOR')")
	public Message updateVendor(@RequestBody Vendor v) {

		Vendor vendor = vService.checkIfPresent(v.getEmail());
		if(vendor!=null){
			vService.updateVendor(v.getFirstname(),v.getLastname(),v.getPassword(),v.getVendorId());
			status = "Vendor "+ vendor.getFirstname()+" "+vendor.getLastname()+" updated";
		}else{
			status = "No such vendor exists";
		}

		return new Message(status);
	}

	@PostMapping("/addInfluencerToList")
	@PreAuthorize("hasAuthority('ROLE_VENDOR')")
	public Message  addInfluencerToList(@RequestBody Influencer influencer){
		Influencer influ = influencerService.getInfluencerById(influencer.getInfluencerId());
		Vendor v = vService.checkIfPresent(influencer.getVendorsList().get(0).getEmail());
		influencerList.add(influ);
		v.setAddedInfluencers(influencerList);

		System.out.println(v.getAddedInfluencers());
		return new Message("Influencer added");
	}

	@PostMapping("/getAddedInfluencers")
	@PreAuthorize("hasAuthority('ROLE_VENDOR')")
	public List<Influencer> getAddedInfluencer(@RequestBody Vendor v){
		Vendor vendor = vService.checkIfPresent(v.getEmail());
		return vendor.getAddedInfluencers();
	}
}