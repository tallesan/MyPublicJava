package com.example.userinfo.userInfo.controller;

import com.example.userinfo.userInfo.model.Position;
import com.example.userinfo.userInfo.model.Users;
import com.example.userinfo.userInfo.service.UserService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/")
public class DefaultController {

  UserService userService;


  @Autowired
  public void setDefaultController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/")
  public String get(Model model) {
    return "redirect:/index";
  }

  @GetMapping("/index")
  public String index(Model model) {
    List<Users> usersInfoAll = userService.findAll();
    model.addAttribute("usersCount", usersInfoAll.size());
    model.addAttribute("usersInfoAll", usersInfoAll);
    return "index";
  }

  @GetMapping("/showAddItemForm")
  public String showAddItemForm(Model model) {
    Users newUsersItem = new Users();
    List<Position> listPos = userService.findPosition();

    model.addAttribute("listPos",listPos);
    model.addAttribute("userItem", newUsersItem);
    return "new_item";
  }

  @PostMapping("/saveTodoItem")
  public String saveTodoItem(
      @ModelAttribute("userItem") @Valid @NotNull @RequestBody Users userItem, BindingResult errors, RedirectAttributes redirectAttributes) {
    if (errors.hasErrors()) {
      redirectAttributes.addFlashAttribute("userItem", userItem);
      redirectAttributes.addFlashAttribute(errors);
      return "redirect:/showAddItemForm";
    }
    userService.saveUsers(userItem);
    return "redirect:/index";
  }

  @GetMapping("/updateUserItem/{id}")
  public String updateUserItem(@PathVariable(value = "id") Long id, Model model) {
    Users userItem = userService.getUsersById(id);

    List<Position> listPos = userService.findPosition();
    model.addAttribute("listPos",listPos);
    model.addAttribute("userItem", userItem);
    return "update_item";
  }

  @PostMapping(path = "/updateUserItem/{id}")
  public String updateTodoItem(@PathVariable("id") Long id,  @ModelAttribute("userItem")
      @Valid @NotNull @RequestBody Users usersItem, BindingResult errors) {
    if (errors.hasErrors()) {
      //return "redirect:/updateUserItem/{" + id + "}";
      return "update_item";
    }
    userService.updateUsers(id, usersItem);
    return "redirect:/index";
  }

  @GetMapping("/deleteUserItem/{id}")
  public String deleteTodoItem(@PathVariable Long id) {
    userService.deleteUsers(id);
    return "redirect:/index";
  }

  @GetMapping("/deleteAll")
  public String deleteAllItem() {
    userService.deleteAllUsers();
    return "redirect:/index";
  }
}
