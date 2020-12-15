package com.example.autoRepair.service;

import com.example.autoRepair.model.UserCar;
import com.example.autoRepair.repository.UserCarRepository;
import com.example.autoRepair.service.interfaces.UserCarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCarServiceImpl implements UserCarService {

  private final UserCarRepository userCarRepository;

  @Autowired
  public UserCarServiceImpl(UserCarRepository userCarRepository) {
    this.userCarRepository = userCarRepository;
  }

  @Override
  public UserCar getUserCarById(Long id) {
    return userCarRepository.findById(id).get();
  }

  @Override
  public void saveUserCar(UserCar userCar) {
    userCarRepository.save(userCar);
  }

  @Override
  public void updateUserCar(Long id, UserCar userCar) {
    UserCar updateUserCarItem = userCarRepository.findById(id).get();
    updateUserCarItem.setModel(userCar.getModel());
    updateUserCarItem.setColor(userCar.getColor());
    updateUserCarItem.setYear(userCar.getYear());
    updateUserCarItem.setPower(userCar.getPower());
    updateUserCarItem.setTypeCar(userCar.getTypeCar());
    updateUserCarItem.setConditioner(userCar.getConditioner());
    updateUserCarItem.setDoorNum(userCar.getDoorNum());
    updateUserCarItem.setUpholstery(userCar.getUpholstery());
    userCarRepository.save(updateUserCarItem);
  }

  @Override
  public void deleteUserCar(Long id) {
    userCarRepository.deleteById(id);
  }

  @Override
  public void deleteAllUserCar() {
    userCarRepository.deleteAll();
  }

  @Override
  public List<UserCar> findAllUserCar() {
    Iterable<UserCar> carIterable = userCarRepository.findAll();
    List<UserCar> userCars = new ArrayList<>();
    for (UserCar userCar: carIterable) {
      userCars.add(userCar);
    }
    return userCars;
  }
}
