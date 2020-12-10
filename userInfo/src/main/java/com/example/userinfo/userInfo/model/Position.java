package com.example.userinfo.userInfo.model;

//
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor

public enum Position {
  Director("Директор"),
  Developer("Разработчик"),
  Manager("Менеджер"),
  Finance("Финансист"),
  Administrator("Администратор");

  private String pos;

  Position(String pos) {
    this.pos = pos;
  }

  public String getPos() {
    return pos;
  }

  public void setPos(String pos) {
    this.pos = pos;
  }
}
