package com.example.autoRepair.controller;

import com.example.autoRepair.dto.UserCarModel;
import com.example.autoRepair.model.UserCar;
import com.example.autoRepair.model.UserQuest;
import com.example.autoRepair.service.UserCarModelService;
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
public class UserCarController {

  private final UserCarModelService userCarModelService;

  private final UserCarServiceImpl userCarService;

  private final UserQuestServiceImpl userQuestService;

  @Autowired
  public UserCarController(UserCarModelService userCarModelService,
      UserCarServiceImpl userCarService,
      UserQuestServiceImpl userQuestService) {
    this.userCarModelService = userCarModelService;
    this.userCarService = userCarService;
    this.userQuestService = userQuestService;
  }

  @GetMapping("/addUserCar")
  public String addUserCar(Model model) {
    List<UserCar> userCars = userCarService.findAllUserCar();
    model.addAttribute("userCarCount", userCars.size());
    model.addAttribute("userCars", userCars);
    return "add_UserCar";
  }

  @GetMapping("/addUserCarForm")
  public String userForm(Model model) {
    UserCarModel userModel = userCarModelService.getUserCarModel();
    UserCar userCar = new UserCar();
    model.addAttribute("userCar", userCar);
    model.addAttribute("userModel", userModel);
    return "user_form";
  }

  @PostMapping("/addUserCarForm")
  public String addUserForm(UserCar userCar) {
    userCarService.saveUserCar(userCar);
    return "redirect:/addUserCar";
  }

  @GetMapping("/updateUserCar/{id}")
  public String updateUserCar(@PathVariable(value = "id") Long id, Model model) {
    UserCar userCar = userCarService.getUserCarById(id);
    UserCarModel userModel = userCarModelService.getUserCarModel();
    model.addAttribute("userModel", userModel);
    model.addAttribute("userCar", userCar);
    return "update_userCar";
  }

  @PostMapping("/updateUserCar/{id}")
  public String updUserCar(@PathVariable(value = "id") Long id, UserCar userCar) {
    userCarService.updateUserCar(id, userCar);
    return "redirect:/addUserCar";
  }

  @GetMapping("/deleteUserCar/{id}")
  public String deleteUserCar(@PathVariable(value = "id") Long id) {
    userCarService.deleteUserCar(id);
    return "redirect:/addUserCar";
  }

  @GetMapping("/commentUserCar/{id}")
  public String commentUserCar(@PathVariable(value = "id") Long id, Model model) {
    UserCar userCar = userCarService.getUserCarById(id);
    UserQuest userQuest = new UserQuest();
    model.addAttribute("userQuest", userQuest);
    model.addAttribute("userCar", userCar);
    return "comment_userCar";
  }

  @PostMapping("/commentUserCar/{id}")
  public String saveCommentUserCar(@PathVariable(value = "id") Long id, UserQuest userQuest) {
    UserCar userCar = userCarService.getUserCarById(id);
    userQuestService.save(userQuest, userCar);
    return "redirect:/addUserCar";
  }




}
