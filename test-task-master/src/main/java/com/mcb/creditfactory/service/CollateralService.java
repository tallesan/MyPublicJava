package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// TODO: reimplement this
@Service
public class CollateralService {

  @Autowired
  private CarService carService;

  @Autowired
  private AirplaneService airplaneService;

  @SuppressWarnings("ConstantConditions")
  public Long saveCollateral(Collateral object) {
    if (!(object instanceof CarDto)) {
      throw new IllegalArgumentException();
    }

    CarDto car = (CarDto) object;
    boolean approved = carService.approve(car);
    if (!approved) {
      return null;
    }

    return Optional.of(car)
        .map(carService::fromDto)
        .map(carService::save)
        .map(carService::getId)
        .orElse(null);
  }

  public Collateral getInfo(Collateral object) {
    if (!(object instanceof CarDto)) {
      throw new IllegalArgumentException();
    }

    return Optional.of((CarDto) object)
        .map(carService::fromDto)
        .map(carService::getId)
        .flatMap(carService::load)
        .map(carService::toDTO)
        .orElse(null);
  }

  public Long saveCollateralAir(Collateral object) {
    if (!(object instanceof AirplaneDto)) {
      throw new IllegalArgumentException();
    }

    AirplaneDto airplaneDto = (AirplaneDto) object;
    boolean approved = airplaneService.approve(airplaneDto);
    if (!approved) {
      return null;
    }

    return Optional.of(airplaneDto)
        .map(airplaneService::fromDto)
        .map(airplaneService::save)
        .map(airplaneService::getId)
        .orElse(null);
  }

  public Collateral getInfoAir(Collateral object) {
    if (!(object instanceof AirplaneDto)) {
      throw new IllegalArgumentException();
    }

    return Optional.of((AirplaneDto) object)
        .map(airplaneService::fromDto)
        .map(airplaneService::getId)
        .flatMap(airplaneService::load)
        .map(airplaneService::toDTO)
        .orElse(null);
  }
}
