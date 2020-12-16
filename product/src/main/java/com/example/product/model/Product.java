package com.example.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  String name;
  @Column(name = "small_text")
  String smallText;
  @Column(name = "full_text")
  String fullText;

}
