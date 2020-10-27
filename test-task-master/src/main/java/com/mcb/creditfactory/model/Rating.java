package com.mcb.creditfactory.model;

import com.mcb.creditfactory.external.CollateralType;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@Table(name = "RATING")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(name = "id_transport")
  Long idTransport;
  @Column(name = "data_rating")
  LocalDate date;
  CollateralType type;
  @Column(name = "assessed_value")
  BigDecimal value;
  BigDecimal rating;

  public Rating(Long idTransport, LocalDate date, CollateralType type, BigDecimal value, BigDecimal rating) {
    this.idTransport = idTransport;
    this.date = date;
    this.type = type;
    this.value = value;
    this.rating = rating;
  }
}
