package com.vendorinfluencer.controller;

import java.util.List;
import com.vendorinfluencer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.vendorinfluencer.dto.Message;
import com.vendorinfluencer.entity.Product;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;

	@PostMapping("/addProduct")
	@PreAuthorize("hasAuthority('ROLE_VENDOR')")
	public Message addProduct( @RequestBody Product product ) {
		productService.addProductDetails(product);
		return new Message("Product Added Successfully");
	}

	@PostMapping("/deleteProduct")
	@PreAuthorize("hasAuthority('ROLE_VENDOR')")
	public Message deleteProductById(@RequestBody Product product) {
		productService.deleteProduct(product.getProductId());
		return new Message("Product Deleted Successfully");
	}
	
	@PostMapping("/showAllProducts")
	@PreAuthorize("hasAuthority('ROLE_VENDOR')")
	public List<Product> showAllProducts(@RequestBody  Product product){
		return productService.getAllProducts(product.getVendorId());
	}
}