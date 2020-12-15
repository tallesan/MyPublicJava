package com.example.autoRepair.controller;

import com.example.autoRepair.model.Car;
import com.example.autoRepair.service.CarServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {

  private CarServiceImpl carService;

  @Autowired
  public CarController(CarServiceImpl carService) {
    this.carService = carService;
  }

  @GetMapping("/addCar")
  public String addCar(Model model) {
    List<Car> cars = carService.findAll();
    model.addAttribute("carCount", cars.size());
    model.addAttribute("cars", cars);
    return "add_car";
  }

  @GetMapping("/showAddCarForm")
  public String newCar(Model model) {
    Car carItem = new Car();

    model.addAttribute("carItem", carItem);
    return "new_car";
  }

  @PostMapping("/saveNewCar")
  public String saveNewCar(Car newCarItem) {
    carService.saveCar(newCarItem);
    return "redirect:/addCar";
  }

  @GetMapping("/updateCar/{id}")
  public String updateCar(@PathVariable(value = "id") Long id, Model model) {
    Car carItem = carService.getCarById(id);
    model.addAttribute("carItem", carItem);
    return "update_car";
  }

  @PostMapping("/updateCarItem/{id}")
  public String updateThisCar(@PathVariable(value = "id") Long id, Car carItem) {
    carService.updateCar(id, carItem);
    return "redirect:/addCar";
  }

  @GetMapping("/deleteCar/{id}")
  public String deleteCarItem(@PathVariable(value = "id") Long id) {
    carService.deleteCar(id);
    return "redirect:/addCar";
  }

  @GetMapping("/deleteAll")
  public String deleteAll() {
    carService.deleteAllCar();
    return "redirect:/addCar";
  }
}
