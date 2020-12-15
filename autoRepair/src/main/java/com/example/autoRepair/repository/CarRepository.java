package com.example.autoRepair.repository;

import com.example.autoRepair.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
