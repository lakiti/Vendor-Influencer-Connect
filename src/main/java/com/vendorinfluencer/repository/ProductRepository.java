package com.vendorinfluencer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vendorinfluencer.entity.Product;

import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	@Transactional
	@Modifying
	@Query("UPDATE Product SET productName= :productName,productCategory = :productCategory, vendorId= :vendorId WHERE productId = :productId")
	void updateProductByproductId(int productId,String productName, String productCategory,int vendorId);

	@Modifying
	@Transactional
	@Query(value = " insert into Product (product_name,product_category,vendor_id) values(:productName,:productCategory,:vendorId)",nativeQuery = true)
	void insertProduct(String productName, String productCategory, int vendorId);

	List<Product> findAllByVendorId(int vendorId);
}
