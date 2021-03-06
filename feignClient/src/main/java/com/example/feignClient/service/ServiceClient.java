package com.example.feignClient.service;


import com.example.feignClient.model.UserResponse;
import java.util.Map;

public interface ServiceClient {

  UserResponse getThisDay();

  UserResponse getHistoryDay();

  boolean getCompareMoney(Map<String, Double> changeCurrency,
      Map<String, Double> changeCurrencyHistory,String base);

  Map<String, Double> changeMoney(Map<String, Double> change, String currency);
}
