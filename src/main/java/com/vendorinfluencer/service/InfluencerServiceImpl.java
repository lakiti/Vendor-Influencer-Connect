package com.vendorinfluencer.service;

import java.util.ArrayList;
import java.util.List;

import com.vendorinfluencer.entity.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendorinfluencer.entity.Influencer;
import com.vendorinfluencer.repository.InfluencerRepository;

@Service
public class InfluencerServiceImpl implements InfluencerService{
	
	@Autowired
	InfluencerRepository influencerRepository;

	@Autowired
	VendorService vendorService;

	public List<Influencer> getInfluencers(String category) {
		return influencerRepository.findAllByCategory(category);
	}


	@Override
	public Influencer getInfluencerById(String influencerId) {
		return influencerRepository.findByInfluencerId(influencerId);
	}
}
