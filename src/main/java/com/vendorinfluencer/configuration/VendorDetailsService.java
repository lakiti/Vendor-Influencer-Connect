package com.vendorinfluencer.configuration;

import com.vendorinfluencer.entity.Vendor;
import com.vendorinfluencer.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VendorDetailsService implements UserDetailsService {

    @Autowired
    VendorRepository vendorRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<Vendor> vendor = Optional.ofNullable(vendorRepo.findByEmail(username));

        return vendor.map(VendorDetails::new).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
