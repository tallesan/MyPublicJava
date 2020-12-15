package com.example.autoRepair.service;

import com.example.autoRepair.model.Car;
import com.example.autoRepair.repository.CarRepository;
import com.example.autoRepair.service.interfaces.CarService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

  private CarRepository carRepository;

  @Autowired
  public CarServiceImpl(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  @Override
  public Car getCarById(Long id) {
    return carRepository.findById(id).get();
  }

  @Override
  public void saveCar(Car car) {
    carRepository.save(car);
  }

  @Override
  public void updateCar(Long id, Car car) {
    Car updateCarItem = carRepository.findById(id).get();
    updateCarItem.setModel(car.getModel());
    updateCarItem.setColor(car.getColor());
    updateCarItem.setYear(car.getYear());
    updateCarItem.setPower(car.getPower());
    updateCarItem.setTypeCar(car.getTypeCar());
    updateCarItem.setConditioner(car.getConditioner());
    updateCarItem.setDoorNum(car.getDoorNum());
    updateCarItem.setUpholstery(car.getUpholstery());
    carRepository.save(updateCarItem);
  }

  @Override
  public void deleteCar(Long id) {
    carRepository.deleteById(id);
  }

  @Override
  public void deleteAllCar() {
    carRepository.deleteAll();
  }

  @Override
  public List<Car> findAll() {
    Iterable<Car> carIterable = carRepository.findAll();
    ArrayList<Car> cars = new ArrayList<>();
    for (Car car: carIterable){
      cars.add(car);
    }
    return cars;
  }

  @Override
  public List<String> findAllColor() {
    List<Car> cars = findAll();
    List<String> colors = new ArrayList<>();
    for(Car car:cars){
      if (!colors.contains(car.getColor()))
      colors.add(car.getColor());
    }
    return colors;
  }

  @Override
  public List<String> findAllModel() {
    List<Car> cars = findAll();
    List<String> models = new ArrayList<>();
    for(Car car:cars){
      if (!models.contains(car.getModel()))
        models.add(car.getModel());
    }
    return models;
  }

  @Override
  public List<String> findAllCarType() {
    List<Car> cars = findAll();
    List<String> carType = new ArrayList<>();
    for(Car car:cars){
      if (!carType.contains(car.getTypeCar()))
        carType.add(car.getTypeCar());
    }
    return carType;
  }

  @Override
  public List<String> findAllConditioner() {
    List<Car> cars = findAll();
    List<String> conditioner = new ArrayList<>();
    for(Car car:cars){
      if (!conditioner.contains(car.getConditioner()))
        conditioner.add(car.getConditioner());
    }
    return conditioner;
  }

  @Override
  public List<String> findAllDoorNum() {
    List<Car> cars = findAll();
    List<String> doorNum = new ArrayList<>();
    for(Car car:cars){
      if (!doorNum.contains(car.getDoorNum()))
        doorNum.add(car.getDoorNum());
    }
    return doorNum;
  }

  @Override
  public List<String> findAllUpholstery() {
    List<Car> cars = findAll();
    List<String> upholstery = new ArrayList<>();
    for(Car car:cars){
      if (!upholstery.contains(car.getUpholstery()))
        upholstery.add(car.getUpholstery());
    }
    return upholstery;
  }

  @Override
  public List<Double> findAllPower() {
    List<Car> cars = findAll();
    List<Double> power = new ArrayList<>();
    for(Car car:cars){
      if (!power.contains(car.getPower()))
        power.add(car.getPower());
    }
    return power;
  }

  @Override
  public List<Short> findAllYear() {
    List<Car> cars = findAll();
    List<Short> year = new ArrayList<>();
    for(Car car:cars){
      if (!year.contains(car.getYear()))
        year.add(car.getYear());
    }
    return year;
  }

}
