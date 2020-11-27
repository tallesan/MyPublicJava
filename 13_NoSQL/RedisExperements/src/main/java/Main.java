import java.util.Map;
import redis.clients.jedis.Jedis;

public class Main {

  public static void main(String[] args) {
    final String Cities = "tripPlan";

    Jedis jedis = new Jedis("localhost");
    Map<String, Double> tripPlan = Map.of(
        "Санкт-Петербург", 2000.0,
        "Казань", 2500.0,
        "Кишинев", 15000.0,
        "Барселона", 38000.0,
        "Рим", 38500.0,
        "Париж", 14000.0,
        "Прага", 25000.0,
        "Мадрид", 30000.0,
        "Пекин", 40000.0,
        "Брюссель", 32000.0
    );

    jedis.del(Cities);

    //Добавляем Map в базу
    tripPlan.entrySet().forEach(city -> {
      jedis.zadd(Cities, city.getValue(), city.getKey());
    });

    //Выводим список в отсортированно виде - по увеличению цены
    System.out.println("Самые дешевые - ");
    jedis.zrangeWithScores(Cities, 0, 2).forEach(System.out::println);
    System.out.println();

    //Выводим список в отсортированно виде - по уменьшению цены
    System.out.println("Самые дорогие - ");
    jedis.zrevrangeWithScores(Cities, 0, 2).forEach(System.out::println);

  }

}
