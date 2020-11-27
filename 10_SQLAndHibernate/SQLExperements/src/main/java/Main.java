import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

  public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
    String user = "root";
    String pass = "12345678";
    String sqlQuery = "SELECT pl.course_name,count(pl.course_name)/max(MONTH(pl.subscription_date)) as st, pl.subscription_date FROM PurchaseList pl WHERE YEAR(pl.subscription_date ) = 2018  group by pl.course_name";
    String sqlQueryOld = "SELECT * FROM purchaselist s WHERE YEAR(s.subscription_date ) = 2018";
    String sqlQueryFull = "SELECT pl.course_name,count(pl.course_name)/12 as st, pl.subscription_date FROM PurchaseList pl WHERE YEAR(pl.subscription_date ) = 2018 group by pl.course_name";
    try {
      Connection connection = DriverManager.getConnection(url, user, pass);
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(sqlQueryOld);
      String curentCourse = null;
      float temp = 0;
      int count = 1;
      System.out
          .println("Первый способ================================================================");
      while (resultSet.next()) {

        String courseName = resultSet.getString("course_name");
        if (!courseName.equals(curentCourse)) {
          if (curentCourse != null) {
            temp = (float) count / 12;
            System.out
                .println("Cреднее количество покупок в месяц - " + curentCourse + " - " + temp);
          }
          count = 1;
          curentCourse = resultSet.getString("course_name");
        } else {
          count++;
        }
      }

      System.out
          .println("Второй способ================================================================");
      ResultSet resultSet2 = statement.executeQuery(sqlQueryFull);
      while (resultSet2.next()) {
        String courseName = resultSet2.getString("course_name");
        float thisCourse = resultSet2.getFloat("st");
        System.out
            .println("Cреднее количество покупок в месяц - " + courseName + " - " + thisCourse);
      }

      System.out
          .println("Третий способ================================================================");
      ResultSet resultSet1 = statement.executeQuery(sqlQuery);
      while (resultSet1.next()) {
        String courseName = resultSet1.getString("course_name");
        float thisCourse = resultSet1.getFloat("st");
        System.out
            .println("Cреднее количество покупок в месяц - " + courseName + " - " + thisCourse);
      }

      resultSet.close();
      statement.close();
      connection.close();

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }
}
