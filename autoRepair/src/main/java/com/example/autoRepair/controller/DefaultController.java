package com.example.autoRepair.controller;

import com.example.autoRepair.model.Car;
import com.example.autoRepair.service.CarServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

  @GetMapping("/")
  public String newCar(Model model) {
    return "redirect:/index";
  }

  @GetMapping("/index")
  public String index(Model model) {
    return "index";
  }
}
