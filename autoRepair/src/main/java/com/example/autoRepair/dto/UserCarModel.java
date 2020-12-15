package com.example.autoRepair.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCarModel {
  private List<String> color;
  private List<String> model;
  private List<String> typeCar;
  private List<String> conditioner;
  private List<String> doorNum;
  private List<String> upholstery;
  private List<Double> power;
  private List<Short> year;

}
