package com.gridnine.testing;

import com.gridnine.testing.FilterFlyAny.AllFlyFilters;
import com.gridnine.testing.FilterFlyAny.FalseStartFilter;
import com.gridnine.testing.FilterFlyAny.TimeFilterFly;
import com.gridnine.testing.FilterFlyAny.TimeOfDepartureFilter;
import com.gridnine.testing.TestFilter.AnyFly;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class AdapterFly {

  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd.HH:mm");
  private LinkedList<Date> list = new LinkedList<>();
  private LinkedList<AnyFly> anyFlyList = new LinkedList<>();

  public void setAnyFlyList(List<Segment> segments) throws ParseException {
    this.anyFlyList = parseSegmentClass(segments);
  }

  public List<AnyFly> getAnyFlyList() {
    return anyFlyList;
  }

  public List<Date> getList() {
    return list;
  }


  public void setList(List<Segment> segments) throws ParseException {
    this.list = parseSegment(segments);
  }

  /**
   * Парсим полученные значения и переводим в даты создаем LinkedList. Сохраняем порядок добавления.
   * Пробуем реализовать Паттерн "Адаптер". Адаптируем данные под свой класс
   */

  private LinkedList<Date> parseSegment(List<Segment> segments) throws ParseException {
    LinkedList list = new LinkedList();

    for (Segment s : segments) {
      String dataSegment = s.toString().replaceAll("T", ".").replaceAll("\\[", "")
          .replaceAll("]", "");
      String[] data = dataSegment.split("\\|");
      for (String datum : data) {
        list.add(DATE_FORMAT.parse(datum));
      }
    }
    return list;
  }

  private LinkedList<AnyFly> parseSegmentClass(List<Segment> segments) throws ParseException {
    LinkedList list = new LinkedList();

    for (Segment s : segments) {
      String dataSegment = s.toString().replaceAll("T", ".").replaceAll("\\[", "")
              .replaceAll("]", "");
      String[] data = dataSegment.split("\\|");
      list.add(new AnyFly(data[0],data[1]));
    }
    return list;
  }

  /**
   * Создаем классы фильтров при добавлении нового фильтра создаем просто ещё 1 класс с
   * интерфейсом FlyFilter
   */
  public boolean changeFilter(String arg) {
    {
      switch (arg) {
        case "1":
          System.out.println("Фильтр по времени вылета");
          System.out.println(new TimeFilterFly().filterSegmentClass(anyFlyList));
          return new TimeFilterFly().filterSegment(list);
        case "2":
          System.out.println("Фильтр по времени нахождение на земле");
          System.out.println(new TimeOfDepartureFilter().filterSegmentClass(anyFlyList));
          return new TimeOfDepartureFilter().filterSegment(list);
        case "3":
          System.out.println("Самолеты улетевшие раньше времени");
          System.out.println(new FalseStartFilter().filterSegmentClass(anyFlyList));
          return new FalseStartFilter().filterSegment(list);
        default:
          System.out.println("Все фильтры");
          System.out.println(new AllFlyFilters().filterSegmentClass(anyFlyList));
          return new AllFlyFilters().filterSegment(list);

      }
    }

  }
}
