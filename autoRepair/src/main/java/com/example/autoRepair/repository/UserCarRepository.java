package com.example.autoRepair.repository;

import com.example.autoRepair.model.UserCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCarRepository extends JpaRepository<UserCar,Long> {

}
