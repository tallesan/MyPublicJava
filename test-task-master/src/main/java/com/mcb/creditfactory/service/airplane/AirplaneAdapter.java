package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.external.ExternalApproveService;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AirplaneAdapter implements CollateralObject {

  private AirplaneDto airplaneDto;

  public AirplaneAdapter(AirplaneDto airplaneDto) {
    this.airplaneDto = airplaneDto;
  }

  @Override
  public BigDecimal getValue() {
    ExternalApproveService externalApproveService = new ExternalApproveService();
    return externalApproveService.approvePlane(airplaneDto);
  }
  @Override
  public Short getYear() {
    return airplaneDto.getYear();
  }

  @Override
  public LocalDate getDate() {
    return LocalDate.now();
  }

  @Override
  public CollateralType getType() {
    return CollateralType.AIRPLANE;
  }
}
