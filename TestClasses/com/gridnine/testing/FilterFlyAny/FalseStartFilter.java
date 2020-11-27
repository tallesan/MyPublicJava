package com.gridnine.testing.FilterFlyAny;

import com.gridnine.testing.TestFilter.AnyFly;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class FalseStartFilter implements FilterFly,FilterFlyClass {
  /**
   * Самолеты вылетевшие раньше времени
   * */

  @Override
  public boolean filterSegment(LinkedList<Date> list) {
    boolean isCorrect = true;
    Calendar calendar = Calendar.getInstance();
    for (int i = 0; i < list.size(); i += 2) {
      if (i + 1 <= list.size()) {
        if (calendar.before(list.get(i))) {
          return false;
        }
      }
    }
    return isCorrect;
  }

  @Override
  public boolean filterSegmentClass(LinkedList<AnyFly> list) {
    boolean isCorrect = true;
    Calendar calendar = Calendar.getInstance();
    for (AnyFly anyFly : list) {
      if (calendar.before(anyFly.getStart())) {
        return false;
      }
    }
    return isCorrect;
  }
}
