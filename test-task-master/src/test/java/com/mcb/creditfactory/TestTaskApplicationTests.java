package com.mcb.creditfactory;

import static org.assertj.core.api.Assertions.assertThat;

import com.mcb.creditfactory.controller.AirplaneController;
import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(AirplaneController.class)
public class TestTaskApplicationTests {

//  @MockBean
//  private AirplaneRepository airplaneRepository;
//
//  @Autowired
//  private MockMvc mockMvc;

  @Autowired
  private AirplaneController airplaneController;
//  @Autowired
//  AirplaneDto airplaneDto;
//
//  @Autowired
//  AirplaneService airplaneService;
//  Airplane airplane1 = new Airplane(1L, "Boeing 747-400", "747-400", "Boeing", (short) 2020, 6000,
//      520);
//  Airplane airplane2 = new Airplane(2L, "Ту 134", "134", "Ту", (short) 2010, 6000, 96);

  @Test
  public void testAirController() throws Exception {
    assertThat(airplaneController).isNotNull();
  }


  @Test
  public void contextLoads() {
  }

}
