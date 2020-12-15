package com.example.autoRepair.controller;

import com.example.autoRepair.model.UserCar;
import com.example.autoRepair.model.UserQuest;
import com.example.autoRepair.service.UserCarServiceImpl;
import com.example.autoRepair.service.UserQuestServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OperatorController {

  private final UserCarServiceImpl userCarService;
  private final UserQuestServiceImpl userQuestService;

  @Autowired
  public OperatorController(UserCarServiceImpl userCarService,
      UserQuestServiceImpl userQuestService) {
    this.userCarService = userCarService;
    this.userQuestService = userQuestService;
  }

  @GetMapping("/repairAuto")
  public String operatorInit(Model model) {
    List<UserCar> userCars = userCarService.findAllUserCar();
    model.addAttribute("userCars", userCars);
    return "operator_init";
  }

  @GetMapping("/responseUserQuest/{id}")
  public String operatorResponse(@PathVariable(value = "id") Long id, Model model) {
    UserCar userCar = userCarService.getUserCarById(id);
    UserQuest userQuest = userQuestService.loadById(userCar.getUserQuests().getId());
    model.addAttribute("userCar", userCar);
    model.addAttribute("userQuest",userQuest);
    return "response_operator";
  }

  @PostMapping("/responseUserCar/{id}")
  public String operatorComment(@PathVariable(value = "id") Long id, UserQuest userQuest) {
    userQuestService.update(id, userQuest);
    return "redirect:/repairAuto";
  }

  @GetMapping("/calculateUserCar/{id}")
  public String calculateUserCar(@PathVariable(value = "id") Long id, Model model){
    UserCar userCar = userCarService.getUserCarById(id);
    model.addAttribute("userCar",userCar);
    return "calculate_userCar";
  }

}
