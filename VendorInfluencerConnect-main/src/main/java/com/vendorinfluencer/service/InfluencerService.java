package com.vendorinfluencer.service;

import com.vendorinfluencer.entity.Influencer;

import java.util.List;

public interface InfluencerService {

     List<Influencer> getInfluencers(String category);

     Influencer getInfluencerById(String influencerId);
}
