package com.example.userinfo.userInfo.service;


import com.example.userinfo.userInfo.model.Position;
import com.example.userinfo.userInfo.model.Users;
import com.example.userinfo.userInfo.model.UsersRepository;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UserService {

  private UsersRepository usersRepository;

  @Autowired
  public UsersServiceImpl(UsersRepository usersRepository) {
    this.usersRepository = usersRepository;
  }

  @Override
  public Users getUsersById(Long id) {
    return usersRepository.findById(id).get();
  }

  @Override
  public void saveUsers(Users users) {
    usersRepository.save(users);
  }

  @Override
  public void updateUsers(Long id, Users usersItem) {
    Users updateUsersItem = usersRepository.findById(id).get();
    updateUsersItem.setEmail(usersItem.getEmail());
    updateUsersItem.setRegDate(usersItem.getRegDate());
    updateUsersItem.setName(usersItem.getName());
    updateUsersItem.setPassword(usersItem.getPassword());
    updateUsersItem.setPosition(usersItem.getPosition());
    updateUsersItem.setPhone(usersItem.getPhone());
    usersRepository.save(updateUsersItem);
  }

  @Override
  public void deleteUsers(Long id) {
    Optional<Users> optional = usersRepository.findById(id);
    usersRepository.delete(optional.get());
  }

  @Override
  public void deleteAllUsers() {
    usersRepository.deleteAll();
  }

  @Override
  public List<Users> findAll() {
    Iterable<Users> usersIterable = usersRepository.findAll();
    ArrayList<Users> usersInfoAll = new ArrayList<>();
    for (Users users : usersIterable) {
      usersInfoAll.add(users);
    }
    return usersInfoAll;
  }

  @Override
  public List<Position> findPosition() {
    ArrayList<Position> listPos = new ArrayList();
    Collections.addAll(listPos, Position.values());
    return listPos;
  }

}
