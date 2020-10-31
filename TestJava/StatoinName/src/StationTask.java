import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class StationTask {
//Добавил разных станция для тестов
  private static List<Station> STATION_LIST = Arrays.asList(
      new Station("МОСКВА"),
      new Station("КАЗАНЬ"),
      new Station("ТУЛА"),
      new Station("ВЛАДИМИР"),
      new Station("СОЧИ"),
      new Station("ВОРОНЕЖ"),
      new Station("МУРОМ"),
      new Station("ЕЛЕЦ"),
      new Station("АСТРАХАНЬ"),
      new Station("ПЕНЗА"),
      new Station("ТВЕРЬ"),
      new Station("РОСТОВ"),
      new Station("ЕКАТЕРИНБУРГ"),
      new Station("ОМСК"),
      new Station("МОЖГА"),
      new Station("МОЗДОК"),
      new Station("БРЯНСК"),
      new Station("ВЛАДИВОСТОК"),
      new Station("САНКТ-ПЕТЕРБУРГ"),
      new Station("САМАРА"));

  public static void main(String[] args) {
    StationTask task = new StationTask(STATION_LIST);
    System.out.println(task.getStationsByTwoFirstLetters("МО"));
    System.out.println(task.getStationsByTwoFirstLetters("СА"));
    System.out.println(task.getStationsByTwoFirstLetters("АС"));
  }

  private StationTask(List<Station> stationList) {
    STATION_LIST.sort(Comparator.comparing(Station::getName));
  }

//Делим пополам и ищем в разных половинах совпадения.
  private Collection<Station> getStationsByTwoFirstLetters(String prefix) {
    int middle = STATION_LIST.size() / 2;
    int indexEnd;
    int indexStart;
    if (STATION_LIST.get(middle).getName().contains(prefix)) {
      indexStart = middle / 2;
      indexEnd = middle + indexStart;
      return searchStation(indexStart, indexEnd, prefix);
    }
    if (!STATION_LIST.get(middle).getName().contains(prefix)) {
      if (STATION_LIST.get(middle).getName().compareTo(prefix) < 0) {
        return searchStation(middle, STATION_LIST.size(), prefix);
      }
      if (STATION_LIST.get(middle).getName().compareTo(prefix) > 0) {
        return searchStation(0, middle, prefix);
      }
    }
    return null;

    //Можно сравнивать через stream
//        STATION_LIST.stream().filter(station -> station.getName().contains(prefix))
//        .collect(Collectors.toList());
  }

  private Collection<Station> searchStation(int start, int end, String prefix) {
    List<Station> list = new ArrayList<>();
    for (int i = start; i < end; i++) {
      if (STATION_LIST.get(i).getName().contains(prefix)) {
        list.add(STATION_LIST.get(i));
      }
    }
    return list;
  }

  private static class Station {

    private String name;

    public Station(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    @Override
    public String toString() {
      return "Station{" +
          "name='" + name + '\'' +
          '}';
    }
  }
}
