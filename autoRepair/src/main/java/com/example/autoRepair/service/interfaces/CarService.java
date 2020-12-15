package com.example.autoRepair.service.interfaces;

import com.example.autoRepair.model.Car;
import java.util.List;

public interface CarService {
  Car getCarById (Long id);
  void saveCar(Car car);
  void updateCar(Long id, Car car);
  void deleteCar(Long id);
  void deleteAllCar();
  List<Car> findAll();
  List<String> findAllColor();
  List<String> findAllModel();
  List<String> findAllCarType();
  List<String> findAllConditioner();
  List<String> findAllDoorNum();
  List<String> findAllUpholstery();
  List<Double> findAllPower();
  List<Short> findAllYear();

}
