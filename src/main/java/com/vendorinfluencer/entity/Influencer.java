package com.vendorinfluencer.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Table(name = "influencer")
@Entity
public class Influencer {

	@Id
	private String influencerId;
	private String influencerName;
	private String followers;
	private String socialMediaHandle;
	private String engagementRate;
	private String contactDetails;
	private String category;

	@ManyToMany(mappedBy = "addedInfluencers", cascade = CascadeType.MERGE)
//	@JsonIgnore
	private List<Vendor> vendorsList;
}
