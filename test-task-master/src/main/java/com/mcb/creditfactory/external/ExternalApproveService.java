package com.mcb.creditfactory.external;

import com.mcb.creditfactory.dto.AirplaneDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

@Service
@Slf4j
public class ExternalApproveService {

  private static final LocalDate MIN_ASSESS_DATE = LocalDate.of(2017, Month.OCTOBER, 1);
  private static final int MIN_CAR_YEAR = 2000;
  private static final BigDecimal MIN_CAR_VALUE = BigDecimal.valueOf(1000000);
  private static final int MIN_PLANE_YEAR = 1991;
  private static final BigDecimal MIN_PLANE_VALUE = BigDecimal.valueOf(230000000);

  public BigDecimal approve(CollateralObject object) {
    if (object.getDate() == null || object.getYear() == null || object.getValue() == null
        || object.getType() == null) {
      return BigDecimal.valueOf(-1);
    }

    BigDecimal code;
    switch (object.getType()) {
      case CAR:
        code = approveCar(object);
        break;
      case AIRPLANE:
        //Ненужна для объектов без цены
        code = approvePlane((AirplaneDto) object);
        break;
      default:
        code = BigDecimal.valueOf(-100);
    }
    return code;
  }

  private BigDecimal approveCar(CollateralObject object) {
    if (object.getYear() < MIN_CAR_YEAR) {
      return valuePercent(10, MIN_CAR_VALUE);
    }
    if (object.getDate().isBefore(MIN_ASSESS_DATE)) {
      return valuePercent(11, MIN_CAR_VALUE);
    }
    if (object.getValue().compareTo(MIN_CAR_VALUE) < 0) {
      return valuePercent(12, MIN_CAR_VALUE);
    }

    return MIN_CAR_VALUE;
  }

  //Без начальной цены объекты проверяются только по дате выпуска
  public BigDecimal approvePlane(AirplaneDto object) {
    if (object.getYear() < MIN_PLANE_YEAR) {
      return valuePercent(20, MIN_PLANE_VALUE);
    }
    return MIN_PLANE_VALUE;
  }

  //выводим сразу проценты
  private BigDecimal valuePercent(int percent, BigDecimal transportValue) {
    BigDecimal value = new BigDecimal(String.valueOf(transportValue));
    value = value.divide(BigDecimal.valueOf(100));
    value = value.multiply(BigDecimal.valueOf(percent));
    final BigDecimal subtract = transportValue.subtract(value);
    return subtract;
  }
}
