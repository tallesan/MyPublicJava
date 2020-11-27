import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

public class Main {

  public static void main(String[] args) {
    Jedis jedis = new Jedis("localhost");
    String userKey = "user";

    List<User> users = new ArrayList<User>();
    for (int i = 0; i < 20; i++) {
      users.add(new User("Пользователь - " + i));
    }
    jedis.del(userKey);//очищаем базу
    for (User user : users) {
      jedis.rpush(userKey, user.getName());
    }

    int indexForPercent = 0;
    for (; ; ) {
      String user = jedis.lpop(userKey);
      System.out.println(user);
      jedis.rpushx(userKey, user);

      if (++indexForPercent % 10 == 0) {
        int randomUserNumber = (int) (Math.random() * jedis.llen(userKey));
        String randomUser = jedis.lindex(userKey, randomUserNumber);
        jedis.lrem(userKey, 1, randomUser);
        jedis.linsert(userKey, ListPosition.AFTER, user, randomUser);
        System.out.printf("%s оплатил платную услугу \n", randomUser + "<=");
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();

      }
    }
  }


}
