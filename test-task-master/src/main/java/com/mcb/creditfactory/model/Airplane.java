package com.mcb.creditfactory.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AIRPLANE")
public class Airplane {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String brand;
  private String model;
  private String manufacturer;

  @Column(name = "year_of_issue")
  private Short year;
  @Column(name = "FUELCAPACITY")
  private Integer fuelCapacity;
  @Column(name = "SEATS")
  private Integer seat;

}

/*create table AIRPLANE (
  id IDENTITY primary key,ID BRAND MODEL MANUFACTURER YEAR_OF_ISSUE FUELCAPACITY  SEATS
  brand VARCHAR2(150),
  model VARCHAR2(200),
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuelCapacity INT,
  seats INT
);*/