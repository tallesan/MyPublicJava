import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Main {

  public static void main(String[] args) {
    String[] version = {"1.2.3", "2.4.32", "2.4.31", "3.17.32", "2.5.12"};
    //способ 1 заводим все значения в отсортированный список, выводим последний элемент или все элементы сразу
    TreeSet<String> versionTreeSet = new TreeSet<>(Arrays.asList(version));
    System.out.println("TreeSet элементы");
    versionTreeSet.forEach(System.out::println);

    //сортируем с помощью коллекции
    List<String> listOfStrings = Arrays.asList(version);
    Collections.sort(listOfStrings, String.CASE_INSENSITIVE_ORDER);
    System.out.println("Сортировка коллекции List");
    listOfStrings.forEach(System.out::println);

    //пузырек
    System.out.println("Сортировка пузырьком");
    lastVersion(version);

    //Сортировка stream
    Arrays.stream(version).sorted().forEach(System.out::println);
  }

  public static void lastVersion(String[] version) {
    String temp;
    for (int i = 0; i < version.length; i++) {
      for (int j = 1; j < version.length - i; j++) {
        if ((version[j - 1].compareTo(version[j]) > 0)) {
          temp = version[j - 1];
          version[j - 1] = version[j];
          version[j] = temp;
        }
      }
    }
    for (String s : version) {
      System.out.println(s);
    }
  }
}
