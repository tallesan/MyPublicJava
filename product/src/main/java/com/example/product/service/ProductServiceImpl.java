package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public List<Product> findAll() {
    Iterable<Product> productIterable = productRepository.findAll();
    List<Product> products = new ArrayList<>();
    for (Product product: productIterable){
      products.add(product);
    }
    return products;
  }

  @Override
  public Product findById(Long id) {
    Product product = productRepository.findById(id).get();
    return product;
  }

  @Override
  public void updateProduct(Long id, Product product) {
    Product productItem = productRepository.getOne(id);
    productItem.setName(product.getName());
    productItem.setSmallText(product.getSmallText());
    productItem.setFullText(product.getFullText());
    productRepository.save(productItem);
  }

  @Override
  public void saveProduct(Product product) {
    productRepository.save(product);

  }

  @Override
  public List<Product> findByName(String name) {
    Iterable<Product> productIterable = productRepository.findAll();
    List<Product> productList = new ArrayList<>();
    for (Product product: productIterable) {
      if (product.getName().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT))){
        productList.add(product);
      }
    }
    return productList;
  }

  @Override
  public void deleteProduct(Long id) {
    productRepository.deleteById(id);
  }

  @Override
  public void deleteAll() {
    productRepository.deleteAll();
  }
}
