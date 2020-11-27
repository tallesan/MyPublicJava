package com.gridnine.testing;

import java.text.ParseException;
import java.util.List;

public class Loader {

  public static void main(String[] args) throws ParseException {

    List<Flight> list = FlightBuilder.createFlights();
    AdapterFly adapterFly = new AdapterFly();
    for (Flight flight : list){
      System.out.println(flight);
      adapterFly.setList(flight.getSegments());
      System.out.println(adapterFly.changeFilter("4"));
    }
  }
}
