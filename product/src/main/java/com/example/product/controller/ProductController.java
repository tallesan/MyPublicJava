package com.example.product.controller;

import com.example.product.model.Product;
import com.example.product.model.SearchAtr;
import com.example.product.service.ProductServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductController {

  ProductServiceImpl productService;

  @Autowired
  public ProductController(ProductServiceImpl productService) {
    this.productService = productService;
  }

  @GetMapping("/")
  public String init(Model model) {
    return "redirect:/index";
  }

  @GetMapping("/index")
  public String initProd(Model model) {
    SearchAtr searchAtr = new SearchAtr();
    List<Product> products = productService.findAll();
    model.addAttribute("searchN", searchAtr);
    model.addAttribute("products", products);
    return "index";
  }

  @GetMapping("/addProduct")
  public String addProduct(Model model) {
    Product product = new Product();
    model.addAttribute("product", product);
    return "add_product";
  }

  @PostMapping("/saveNewProduct")
  public String newProduct(Product product) {
    productService.saveProduct(product);
    return "redirect:/index";
  }

  @PostMapping("/searchName")
  public String searchByName(SearchAtr searchAtr, Model model) {
    List<Product> products = productService.findByName(searchAtr.getSearchName());
    if (products.size() == 0) {
      model.addAttribute("searchFalse",false);
    }
    model.addAttribute("products", products);
    return "search_product";
  }

  @GetMapping("/deleteProduct/{id}")
  public String deleteById(@PathVariable(value = "id") Long id) {
    productService.deleteProduct(id);
    return "redirect:/index";
  }

  @GetMapping("/deleteAll")
  public String deleteAll() {
    productService.deleteAll();
    return "redirect:/addCar";
  }

  @GetMapping("/updateProduct/{id}")
  public String updateProduct(@PathVariable(value = "id") Long id, Model model) {
    Product product = productService.findById(id);
    model.addAttribute("product", product);
    return "update_product";
  }

  @PostMapping("/updateProduct/{id}")
  public String updateProductById(@PathVariable(value = "id") Long id, Product product) {
    productService.updateProduct(id, product);
    return "redirect:/index";
  }

}
