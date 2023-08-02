package com.vendorinfluencer.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.ws.rs.QueryParam;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Vendor {
	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
	)
	@GenericGenerator(
			name = "native",
			strategy = "native"
	)
	@QueryParam("vendorId")
	private int vendorId;
	@QueryParam("email")
	private String  email;
	@QueryParam("firstname")
	private String firstname;
	@QueryParam("lastname")
	private String lastname;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@QueryParam("password")
	private String password;
	private String role = "ROLE_VENDOR";
	@QueryParam("isBlocked")
	private int isBlocked;

	@ManyToMany(cascade = CascadeType.MERGE)
	List<Influencer> addedInfluencers;

	@CreationTimestamp
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;


}
