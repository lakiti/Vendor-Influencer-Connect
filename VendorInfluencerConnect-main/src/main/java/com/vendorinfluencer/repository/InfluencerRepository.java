package com.vendorinfluencer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vendorinfluencer.entity.Influencer;


@Repository
public interface InfluencerRepository extends JpaRepository<Influencer, String> {
	List<Influencer> findAllByCategory(String category);

    Influencer findByInfluencerId(String influencerId);
}
