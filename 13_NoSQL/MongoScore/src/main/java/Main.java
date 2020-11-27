import static com.mongodb.client.model.Accumulators.avg;
import static com.mongodb.client.model.Accumulators.max;
import static com.mongodb.client.model.Accumulators.min;
import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Main {

  //
  private static MongoDatabase database = new MongoClient("127.0.0.1", 27017)
      .getDatabase("local");
  //    MongoDatabase database = mongoClient.getDatabase("local");
  private static MongoCollection<Document> production = database.getCollection("Production");
  private static MongoCollection<Document> score = database.getCollection("Score");

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      String command = scanner.nextLine();

      if (command.startsWith("ДОБАВИТЬ_МАГАЗИН")) {
        addScore(command);
      }
      if (command.startsWith("ВЫСТАВИТЬ_ТОВАР")) {
        exposeProduction(command);
      }
      if (command.startsWith("ДОБАВИТЬ_ТОВАР")) {
        addProduction(command);
      }
      if (command.startsWith("СТАТИСТИКА_ТОВАРОВ")) {
        productionStatistics();
      }

    }
  }

  private static void addScore(String command) {
    String[] data = command.split(" ");
    try {
      Document document = new Document();
      document.append("score", data[1]);
      score.insertOne(document);
      System.out.println(data[1] + " - магазин успешно добавлен");
    } catch (Exception ex) {
      System.out.println("Не введено имя магазина");
    }
  }

  private static void addProduction(String command) {

    System.out.println(command);
    String[] data = command.split(" ");
    try {
      Document document = new Document();
      document.append("product", data[1]);
      document.append("price", Integer.parseInt(data[2]));
      production.insertOne(document);
      System.out.println(data[1] + " - товар успешно добавлен");
    } catch (Exception ex) {
      System.out.println("Не правильное имя продукта или цена");
    }
  }

  private static void exposeProduction(String command) {
    String[] data = command.split(" ");
    try {
      if (productScanner(data[1])) {
        score.updateOne(eq("score", data[2]),
            new Document("$addToSet", new Document("product", data[1])));
        System.out.println(data[1] + " - товар успешно добавлен в магазин - " + data[2]);
      } else {
        System.out.println("Продуктов на складе нет");
      }
    } catch (Exception ex) {
      System.out.println("Не правильное имя продукта или цена");
    }
  }

  public static boolean productScanner(Object value) {
    FindIterable<Document> documents = production.find(new Document("product", value));
    return documents.first() != null;
  }

  private static void productionStatistics() {
    //формируем выборку
    BsonDocument query1 = BsonDocument.parse("{$lookup: {from: \"Production\"," +
        "localField: \"product\"," +
        "foreignField: \"product\"," +
        " as: \"products\"}}");

    BsonDocument query2 = BsonDocument.parse("{ $unwind: \"$products\"}");
    //накладываем условия согласно заданию (объединяем чтобы не делать несколько запросов)
    BsonDocument query3 = BsonDocument.parse("{ $group: {_id: \"$product\"," +
        "ProductsCount: {$sum :1}," +                             //суммируем
        "AverageProductsPrice: {$avg:\"$products.price\"}," +     //находим среднее
        "MinPriceProduct: {$min:\"$products.price\"}," +          //Находим минимальное
        "MaxPriceProduct: {$max:\"$products.price\"}," +          //находим максимальное
        "}}");
    BsonDocument query4 = BsonDocument.parse("{$match:{\"products.price\":{$lt : 100}}}");
    BsonDocument query5 = BsonDocument
        .parse("{ $group: { _id: \"$product\", CountProductLess100: {$sum: 1}}}");
    score.aggregate(Arrays.asList(query1, query2, query3))
        .forEach((Consumer<Document>) System.out::println);

    score.aggregate(Arrays.asList(query1, query2, query4, query5))
        .forEach((Consumer<Document>) System.out::println);

    //запросы на Java
    Bson lookup = Aggregates.lookup("Production","product","product","products");
    Bson unwind = Aggregates.unwind("$products");
    score.aggregate(Arrays.asList(lookup, unwind,
          Aggregates.group("product", Accumulators.sum("TotalCost","$products.price"),
              avg("Average","$products.price"),
              max("MaxPrice","$products.price"),
              min("MinPrice","$products.price"),
              sum("CountProduct",1)))).
        forEach((Consumer<Document>) System.out::println);

    score.aggregate(Arrays.asList(lookup,unwind,
        match(lt("products.price",100)),
        Aggregates.group("product", Accumulators.sum("CountLess100",1)))).
        forEach((Consumer<Document>) System.out::println);
  }
}
