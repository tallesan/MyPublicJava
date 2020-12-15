package com.example.autoRepair.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JTextArea;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Controller;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_service")
public class UserCar {
/** Класс для создания пользовательских заявок */
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
  @OneToOne(mappedBy="userCar")
  private UserQuest userQuests;

}
