package com.example.autoRepair.service.interfaces;

import com.example.autoRepair.model.UserCar;
import java.util.List;

public interface UserCarService {
  UserCar getUserCarById (Long id);
  void saveUserCar(UserCar userCar);
  void updateUserCar(Long id, UserCar userCar);
  void deleteUserCar(Long id);
  void deleteAllUserCar();
  List<UserCar> findAllUserCar();
}
