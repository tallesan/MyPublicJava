import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) throws IOException {
    String url = "https://skillbox.ru";

    Set<String> curent = new TreeSet<>(new ForkJoinPool().invoke(new UrlParse(url)));

    Scanner scanner = new Scanner(System.in);
    System.out.println("Выводим список - ");
    System.out.println(curent.size());
    scanner.nextLine();

    curent.forEach(System.out::println);

    PrintWriter writer = new PrintWriter(
        "C:\\JavaProject\\SkillBox\\java_basics\\11_Multithreading\\ParseElementSite\\src\\main\\resources\\text.txt");
    for (String str : curent) {
      StringBuilder stingTab = new StringBuilder();
      String[] tmp = str.split("/");
      if (tmp.length > 3) {
        for (int i = 3; i <= tmp.length-1; i++) {
          stingTab.append("\t");
        }
      }
      writer.write(stingTab + str + "\n");
    }
    writer.flush();
    writer.close();

  }
}

