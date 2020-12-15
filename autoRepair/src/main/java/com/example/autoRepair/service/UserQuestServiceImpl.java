package com.example.autoRepair.service;

import com.example.autoRepair.model.UserCar;
import com.example.autoRepair.model.UserQuest;
import com.example.autoRepair.repository.UserQuestRepository;
import com.example.autoRepair.service.interfaces.UserQuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQuestServiceImpl implements UserQuestService {

  private final UserQuestRepository userQuestRepository;

  @Autowired
  public UserQuestServiceImpl(UserQuestRepository userQuestRepository) {
    this.userQuestRepository = userQuestRepository;
  }

  @Override
  public void save(UserQuest userQuest, UserCar userCar) {
    userQuest.setUserCar(userCar);
    userQuestRepository.save(userQuest);
  }

  @Override
  public void update(Long Id, UserQuest userQuest) {
    UserQuest userQuestUpd = userQuestRepository.findById(Id).get();
    userQuestUpd.setUserQuestion(userQuest.getUserQuestion());
    userQuestUpd.setOperatorResponse(userQuest.getOperatorResponse());
    userQuestRepository.save(userQuestUpd);
  }

  @Override
  public UserQuest loadById(Long id) {
    UserQuest userQuests = userQuestRepository.findById(id).get();
    return userQuests;
  }
}
