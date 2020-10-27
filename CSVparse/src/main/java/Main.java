import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import org.bson.BsonDocument;
import org.bson.Document;

public class Main {

  public static void main(String[] args) throws IOException {
    List<String> students = Files.readAllLines(Paths.get("src/main/resources/mongo.csv"));
    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
    MongoDatabase database = mongoClient.getDatabase("local");
    MongoCollection<Document> collection = database.getCollection("Students");
    collection.drop();

    for (String student : students) {
      String[] data = student.split(",");
      Document document = new Document();
      document.append("name", data[0]).append("year", Integer.parseInt(data[1]));
      StringBuilder builder = new StringBuilder();
      for (int i = 2; i < data.length; i++) {
        builder.append(data[i].replaceAll("\"", "").trim() + " ");
      }
      String courses = builder.toString().trim();
      document.append("courses", courses);
      collection.insertOne(document);
    }
    System.out.printf("В нашу базу добавлено - %d студентов \n", collection.countDocuments());

    BsonDocument manyOld = BsonDocument.parse("{year: {$gt: 40}}");
    System.out.println("Количество студентов старше 40 - " + collection.countDocuments(manyOld));

    collection.find(manyOld)
        .forEach(
            (Consumer<Document>) System.out::println);


    //сортируем по возросту выводим первое значение получаем курсы самого молодого студента
    BsonDocument queryYear = BsonDocument.parse("{year: 1}");
    collection.find().sort(queryYear).limit(1)
        .forEach(
            (Consumer<Document>) document -> System.out
                .println("Самый молодой студент : " + document.get("name")));

    BsonDocument oldStudentCourse = BsonDocument.parse("{year: -1}");
    collection.find().sort(oldStudentCourse).limit(1)
        .forEach(
            (Consumer<Document>) document -> System.out
                .println("Самый молодой студент : " + document.get("courses")));


  }
}
