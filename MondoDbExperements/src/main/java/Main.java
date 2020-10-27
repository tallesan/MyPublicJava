import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.function.Consumer;
import org.bson.BsonDocument;
import org.bson.Document;

public class Main {

  public static void main(String[] args) {
    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    MongoDatabase database = mongoClient.getDatabase("local");
    MongoCollection<Document> collection = database.getCollection("MyBooks");

    collection.drop();

    Document books1 = new Document();
    books1.append("name", "Ник Перумов").append("bookName", "Гибель богов").append("year", 1994);
    collection.insertOne(books1);

    Document books2 = new Document();
    books2.append("name", "Ник Перумов").append("bookName", "Алмазный меч, деревянный меч")
        .append("year", 1998);
    collection.insertOne(books2);

    Document books3 = new Document();
    books3.append("name", "Сергея Лукьяненко").append("bookName", "Не время для драконов")
        .append("year", 1997);
    collection.insertOne(books3);

    Document books4 = new Document();
    books4.append("name", "Джон Рональд Руэл Толкин")
        .append("bookName", "Хоббит, или Туда и обратно").append("year", 1937);
    collection.insertOne(books4);

    Document books5 = new Document();
    books5.append("name", "Михаил Булгаков").append("bookName", "Мастер и Маргарита")
        .append("year", 1967);
    collection.insertOne(books5);

    System.out.printf("В нашу базу добавлено - %d книг \n", collection.countDocuments());

    //выводим значение по автору
    BsonDocument queryAuthor = BsonDocument.parse("{name: \"Ник Перумов\"}");
    collection.find(queryAuthor).forEach((Consumer<Document>) document -> {
      System.out.println("Первый запрос :\n" + document);
    });

    //Сортируем по году и выводим первый элемент
    BsonDocument queryYear = BsonDocument.parse("{year: 1}");
    collection.find().sort(queryYear).limit(1)
        .forEach(
            (Consumer<Document>) document -> System.out.println("Первый запрос :\n" + document));

  }

}
