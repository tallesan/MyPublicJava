package com.gridnine.testing.FilterFlyAny;

import java.util.Date;
import java.util.LinkedList;
/**
 * Интерфейс для классов реализующих фильтры
 * Пробуем реализовать Паттерн "Стратегия"
 * */
public interface FilterFly {
  public boolean filterSegment(LinkedList<Date> dates);
}
