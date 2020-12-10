package com.example.userinfo.userInfo.service;


import com.example.userinfo.userInfo.model.Position;
import com.example.userinfo.userInfo.model.Users;
import java.util.List;

public interface UserService {
  Users getUsersById (Long id);
  void saveUsers(Users users);
  void updateUsers(Long id, Users users);
  void deleteUsers(Long id);
  void deleteAllUsers();
  List<Users> findAll();
  List<Position> findPosition();

}
