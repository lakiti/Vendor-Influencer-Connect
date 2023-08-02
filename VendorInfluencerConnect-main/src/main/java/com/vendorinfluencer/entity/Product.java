package com.vendorinfluencer.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Table(name = "Product")
@Entity
public class Product {

	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
	)
	@GenericGenerator(
			name = "native",
			strategy = "native"
	)
	private int productId;

	private String productName;
	private String productCategory;
	private int vendorId;
}

