package com.example.autoRepair.service.interfaces;

import com.example.autoRepair.model.UserCar;
import com.example.autoRepair.model.UserQuest;

public interface UserQuestService {
  void save(UserQuest userQuest, UserCar userCar);

  void update(Long Id, UserQuest userQuest);

  UserQuest loadById(Long id);
}
