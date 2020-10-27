package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.Rating;
import com.mcb.creditfactory.repository.CarRepository;
import com.mcb.creditfactory.repository.RatingRepository;
import com.mcb.creditfactory.service.car.CarAdapter;
import com.mcb.creditfactory.service.car.CarService;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class CarController {

  @Autowired
  CarRepository carRepository;

  @Autowired
  RatingRepository ratingRepository;

  @Autowired
  CarService carService;

  @Autowired
  ExternalApproveService externalApproveService;

  @GetMapping("/")
  public String newCar(Model model) {
    return "redirect:/index";
  }
  //-----------
  @GetMapping("/index")
  public String index(Model model) {
    Iterable<Car> cars = carRepository.findAll();
    ArrayList<Car> carArrayList = new ArrayList<>();
    for (Car car : cars) {
      carArrayList.add(car);
    }
    model.addAttribute("cars", carArrayList);
    model.addAttribute("carsCount", carArrayList.size());
    Date date = new Date();
    model.addAttribute("date", date.toString());
    return "index";
  }
  @GetMapping("/new_item")
  public String addCar(Model model) {
    Car newCar = new Car();
    model.addAttribute("newCar", newCar);
    return "new_item";
  }

  @PostMapping("/saveNewCar")
  public String saveNewCar(Car newCar) {
    CarAdapter carAdapter = new CarAdapter(carService.toDTO(newCar));
    carService.save(newCar);
    carService.getId(newCar);
    Rating rating = new Rating(newCar.getId(), carAdapter.getDate(), carAdapter.getType(),
        carAdapter.getValue(), externalApproveService.approve(carAdapter));
    ratingRepository.save(rating);
    return "redirect:/";
  }

  @GetMapping("/ratings/{id}")
  public String showRating(@PathVariable Long id, Model model) {
//    Optional<Car> carId = carRepository.findById(id);
    Car car = carRepository.findById(id).get();
    Iterable<Rating> ratings = ratingRepository.findAll();
    ArrayList<Rating> ratingList = new ArrayList<>();
    for (Rating rating : ratings) {
      if (rating.getIdTransport().equals(id) && rating.getType().equals(CollateralType.CAR)) {
        ratingList.add(rating);
      }
    }
    model.addAttribute("car", car);
    model.addAttribute("carId", car.getId());
    model.addAttribute("ratingList", ratingList);
    model.addAttribute("ratingCount", ratingList.size());
    return "rating";
  }

  @GetMapping("/newRating/{id}")
  public String ratingTransport(@PathVariable Long id, Model mode) {
    Car car = carRepository.findById(id).get();
    CarAdapter carAdapter = new CarAdapter(carService.toDTO(car));
    Rating rating = new Rating(car.getId(), carAdapter.getDate(), carAdapter.getType(),
        carAdapter.getValue(), externalApproveService.approve(carAdapter));
    ratingRepository.save(rating);
    return "redirect:/";
  }
}
