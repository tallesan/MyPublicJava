package com.example.autoRepair.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "type_car")
public class Car {
/** класс для создание моделей и комплектации авто */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String color;
  private String model;
  private String typeCar;
  private String conditioner;
  private String doorNum;
  private String upholstery;
  private Double power;
  private Short year;
}
