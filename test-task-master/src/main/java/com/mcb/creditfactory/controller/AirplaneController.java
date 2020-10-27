package com.mcb.creditfactory.controller;

import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.Rating;
import com.mcb.creditfactory.repository.AirplaneRepository;
import com.mcb.creditfactory.repository.RatingRepository;
import com.mcb.creditfactory.service.airplane.AirplaneAdapter;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping(value = "/airindex")
public class AirplaneController {

  @Autowired
  AirplaneRepository airplaneRepository;

  @Autowired
  RatingRepository ratingRepository;

  @Autowired
  AirplaneService airplaneService;

  @Autowired
  ExternalApproveService externalApproveService;

  @GetMapping("/airindex")
  public String index(Model model) {
    Iterable<Airplane> airplanes = airplaneRepository.findAll();
    ArrayList<Airplane> airplaneList = new ArrayList<>();
    for (Airplane airplane : airplanes) {
      airplaneList.add(airplane);
    }
    model.addAttribute("airplanes", airplaneList);
    model.addAttribute("airplanesCount", airplaneList.size());
    Date date = new Date();
    model.addAttribute("date", date.toString());
    return "airindex";
  }

  @GetMapping("/new_airplane")
  public String addAirplane(Model model) {
    Airplane newAirplane = new Airplane();
    model.addAttribute("newAirplane", newAirplane);
    return "new_airplane";
  }

  @PostMapping("/saveNewAirplane")
  public String saveNewAirplane(Airplane newAirplane) {
    airplaneService.save(newAirplane);
    AirplaneAdapter airplaneAdapter = new AirplaneAdapter(airplaneService.toDTO(newAirplane));
    Rating rating = new Rating(newAirplane.getId(), airplaneAdapter.getDate(),
        airplaneAdapter.getType(), airplaneAdapter.getValue(),
        externalApproveService.approvePlane(airplaneService.toDTO(newAirplane)));
    ratingRepository.save(rating);
    return "redirect:/airindex";
  }

  @GetMapping("/ratingAir/{id}")
  public String showRating(@PathVariable Long id, Model model) {
    Airplane airplane = airplaneRepository.findById(id).get();
    Iterable<Rating> ratings = ratingRepository.findAll();
    ArrayList<Rating> ratingList = new ArrayList<>();
    for (Rating rating : ratings) {
      if (rating.getIdTransport().equals(id) && rating.getType().equals(CollateralType.AIRPLANE)) {
        ratingList.add(rating);
      }
    }
    model.addAttribute("airplane", airplane);
    model.addAttribute("airplaneId", airplane.getId());
    model.addAttribute("ratingList", ratingList);
    model.addAttribute("ratingCount", ratingList.size());
    return "ratingAir";
  }

  @GetMapping("/newRatingAir/{id}")
  public String ratingTransport(@PathVariable Long id) {
    Airplane airplane = airplaneRepository.findById(id).get();
    AirplaneAdapter airplaneAdapter = new AirplaneAdapter(airplaneService.toDTO(airplane));
    Rating rating = new Rating(airplane.getId(), airplaneAdapter.getDate(), airplaneAdapter.getType(),
        airplaneAdapter.getValue(), externalApproveService.approvePlane(airplaneService.toDTO(airplane)));
    ratingRepository.save(rating);
    return "redirect:/airindex";
  }

}
