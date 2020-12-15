package com.example.autoRepair.service;

import com.example.autoRepair.dto.UserCarModel;
import com.example.autoRepair.service.interfaces.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/** Класс для удобной передачи в модель выпадающих списков */
@Component
public class UserCarModelService {

  private CarService carService;
  @Autowired
  public UserCarModelService(CarService carService) {
    this.carService = carService;
  }
  public UserCarModel getUserCarModel(){
    UserCarModel userCarModel = new UserCarModel();
    userCarModel.setModel(carService.findAllModel());
    userCarModel.setColor(carService.findAllColor());
    userCarModel.setTypeCar(carService.findAllCarType());
    userCarModel.setConditioner(carService.findAllConditioner());
    userCarModel.setDoorNum(carService.findAllDoorNum());
    userCarModel.setUpholstery(carService.findAllUpholstery());
    userCarModel.setPower(carService.findAllPower());
    userCarModel.setYear(carService.findAllYear());
    return userCarModel;
  }
}
