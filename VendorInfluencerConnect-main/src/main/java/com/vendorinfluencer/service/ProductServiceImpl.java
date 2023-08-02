package com.vendorinfluencer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendorinfluencer.entity.Product;
import com.vendorinfluencer.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	public void addProductDetails(Product product) {
		productRepository.insertProduct(product.getProductName(),product.getProductCategory(),product.getVendorId());
	}
	public void updateProductDetails(int productId, String productName, String productCategory,int vendorId) {
		productRepository.updateProductByproductId(productId,productName,productCategory,vendorId);

	}
	public void deleteProduct(int productId) {
		productRepository.deleteById(productId);
	}
	public List<Product> getAllProducts(int vendorId) {

		return productRepository.findAllByVendorId(vendorId);
	}
}
