package com.example.autoRepair.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user_quest")
public class UserQuest {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;
  @Length(max = 4000)
  String userQuestion;
  //  @Length(max = 4000)
  String operatorResponse;
  //  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn(name = "userCar_id",
//      referencedColumnName = "id",
//      nullable = false, insertable = false, updatable = false)
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//  @JoinColumn(name = "user_service_id")
  UserCar userCar;
}
