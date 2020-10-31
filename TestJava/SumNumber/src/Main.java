import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    int[] value = {3, 5, 2, 6, 4, 25, 7, 9, 17, 1, 10, 9};
    sum(value);
  }

  public static void sum(int[] value) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < value.length; i += 2) {
      int sumValue = value[i] + value[i + 1];
      list.add(sumValue);
      System.out.println(sumValue);
    }
    for (int i = 0; i < list.size(); i++) {
      for (int j = i + 1; j < list.size(); j++) {
        if (list.get(i).equals(list.get(j))) {
          list.remove(j);
          list.remove(i);
        }
      }
    }
    System.out.println(list);
  }
}
