package com.example.userinfo.userInfo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_registration")
public class Users {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @Column(name = "reg_date")
  LocalDate regDate = LocalDate.now();
  @NotBlank(message = "Поле \"ФИО\" не должно быть пустым")
  String name;
//  @NotBlank(message = "Поле \"Должность\" не должно быть пустым")
  Position position;
  @NotBlank(message = "Поле \"Почта\" не должно быть пустым")
  @Email(message = "Не корректный адрес почты")
  String email;
  @NotBlank(message = "Поле \"пароль\" не должно быть пустым")
  String password;
  @NotBlank(message = "Поле \"телефон\" не должно быть пустым")
  @Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d",message = "Не корректный номер \"88001001234\"")
  String phone;
}
