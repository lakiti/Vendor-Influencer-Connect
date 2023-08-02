package com.vendorinfluencer.service;

import com.vendorinfluencer.entity.Product;

import java.util.List;

public interface ProductService {

    void addProductDetails(Product product);

   void updateProductDetails(int productId, String productName, String productCategory,int vendorId);

   void deleteProduct(int productId);

  List<Product> getAllProducts(int vendorId);
}
