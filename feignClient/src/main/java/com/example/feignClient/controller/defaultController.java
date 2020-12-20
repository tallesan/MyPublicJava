package com.example.feignClient.controller;

import com.example.feignClient.model.UserResponse;
import com.example.feignClient.service.ServiceClientImpl;
import com.example.feignClient.service.ServiceGiphy;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class defaultController {

  private ServiceClientImpl serviceClient;
  private ServiceGiphy serviceGiphy;

  @Autowired
  public defaultController(ServiceClientImpl serviceClient, ServiceGiphy serviceGiphy) {
    this.serviceClient = serviceClient;
    this.serviceGiphy = serviceGiphy;
  }

  @RequestMapping("/index")
  public String initClient(Model model) {
    UserResponse userResponceThisDay = serviceClient.getThisDay();
    UserResponse userResponceHistoryDay = serviceClient.getHistoryDay();
    boolean comparison = serviceClient
        .getCompareMoney(userResponceThisDay.getRates(), userResponceHistoryDay.getRates(), "USD");
    String img;
    if (comparison) {
      img = serviceGiphy.getUrlGiphy("rich");
    } else {
      img = serviceGiphy.getUrlGiphy("broke");
    }
    model.addAttribute("img",img);
    model.addAttribute("searchFalse", comparison);
    return "index";
  }

  @GetMapping("/")
  public String getStartProg(Model model) {
    return "redirect:/index";
  }

  @GetMapping("/commentRub")
  public String getRubChange(Model model) {
    UserResponse userResponceThisDay = serviceClient.getThisDay();
    UserResponse userResponceHistoryDay = serviceClient.getHistoryDay();
    Map<String, Double> currencyThis = serviceClient
        .changeMoney(userResponceThisDay.getRates(), "RUB");
    Map<String, Double> currencyHistoryDay = serviceClient
        .changeMoney(userResponceHistoryDay.getRates(), "RUB");
    boolean comparison = serviceClient
        .getCompareMoney(currencyThis, currencyHistoryDay, "RUB");
    String img;
    if (comparison) {
      img = serviceGiphy.getUrlGiphy("rich");
    } else {
      img = serviceGiphy.getUrlGiphy("broke");
    }
    model.addAttribute("img", img);
    model.addAttribute("searchFalse", comparison);
    return "rub_compare";
  }

}
