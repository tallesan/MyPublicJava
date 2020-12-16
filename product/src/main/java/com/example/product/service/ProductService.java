package com.example.product.service;

import com.example.product.model.Product;
import java.util.List;

public interface ProductService {
  List<Product> findAll();
  Product findById(Long id);
  void updateProduct(Long id, Product product);
  void saveProduct(Product product);
  List<Product> findByName(String name);
  void deleteProduct(Long id);
  void deleteAll();
}
