package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.repository.AirplaneRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirplaneServiceImpl implements AirplaneService {

  @Autowired
  private ExternalApproveService approveService;

  @Autowired
  private AirplaneRepository airplaneRepository;

  @Override
  public boolean approve(AirplaneDto dto) {
    return approveService.approve(new AirplaneAdapter(dto)) == BigDecimal.valueOf(0);
  }

  @Override
  public Airplane save(Airplane airplane) {
    return airplaneRepository.save(airplane);
  }

  @Override
  public Optional<Airplane> load(Long id) {
    return airplaneRepository.findById(id);
  }

  @Override
  public Airplane fromDto(AirplaneDto dto) {
    return new Airplane(dto.getId(),
        dto.getBrand(),
        dto.getModel(),
        dto.getManufacturer(),
        dto.getYear(),
        dto.getFuelCapacity(),
        dto.getSeat());
  }

  @Override
  public AirplaneDto toDTO(Airplane airplane) {
    return new AirplaneDto(airplane.getId(),
        airplane.getBrand(),
        airplane.getModel(),
        airplane.getManufacturer(),
        airplane.getYear(),
        airplane.getFuelCapacity(),
        airplane.getSeat());
  }

  @Override
  public Long getId(Airplane airplane) {
    return airplane.getId();
  }
}
