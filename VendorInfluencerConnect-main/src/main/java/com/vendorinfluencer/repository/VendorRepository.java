package com.vendorinfluencer.repository;

import com.vendorinfluencer.entity.Vendor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	 Vendor findByEmail(String email);

	@Transactional
	@Modifying
	@Query("UPDATE Vendor SET  firstname= :firstname,lastname = :lastname, password= :password WHERE vendorId= :vendorId")
	void updateVendorByVendorId(String firstname, String lastname,String password,int vendorId);

    Vendor findByVendorId(int id);
}
