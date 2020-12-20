package com.example.feignClient.service;


import com.example.feignClient.client.UserClient;
import com.example.feignClient.model.ParamQuery;
import com.example.feignClient.model.UserResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClientImpl implements ServiceClient {

  private final ParamQuery paramQuery;
  private final UserClient client;

  @Autowired
  public ServiceClientImpl(UserClient client, ParamQuery paramQuery) {
    this.client = client;
    this.paramQuery = paramQuery;
  }

  @Override
  public UserResponse getThisDay() {
    UserResponse userResponce =
        client.getUser(paramQuery.getAppId(), paramQuery.getBase());
    return userResponce;
  }

  @Override
  public UserResponse getHistoryDay() {
    LocalDateTime localdate = LocalDateTime.now().minusDays(1);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String date = formatter.format(localdate);
//    System.out.println(date);

    UserResponse userResponce = client
        .getUserHistory(date, paramQuery.getAppId(), paramQuery.getBase());
    return userResponce;
  }
/**Добавлена переменная base для удобной конвертации RUB. Чтобы не писать ещё 1 метод.*/
  @Override
  public boolean getCompareMoney(Map<String, Double> changeCurrency,
      Map<String, Double> changeCurrencyHistory,String base) {
//    double thisDay = changeCurrency.get(paramQuery.getOpponent());
    double thisDay = changeCurrency.get(paramQuery.getOpponent());
    double historyDay = changeCurrencyHistory.get(paramQuery.getOpponent());
//    System.out.println(thisDay + " - " + historyDay);
    return thisDay > historyDay;
  }

  @Override
  public Map<String, Double> changeMoney(Map<String, Double> changeCurrency, String currency) {
    Double rub = changeCurrency.get(currency);
    for (String search : changeCurrency.keySet()) {
      Double tmp = changeCurrency.get(search);
      changeCurrency.put(search, rub / tmp);
    }
    changeCurrency.put("USD", rub);
    return changeCurrency;
  }

}
