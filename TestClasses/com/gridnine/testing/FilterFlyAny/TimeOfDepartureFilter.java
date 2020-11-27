package com.gridnine.testing.FilterFlyAny;

import com.gridnine.testing.TestFilter.AnyFly;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class TimeOfDepartureFilter implements FilterFly, FilterFlyClass {
/**
 * время нахождения на земле больше или равно 2-м часам.
 * */
  @Override
  public boolean filterSegment(LinkedList<Date> list) {
    boolean isCorrect = true;
    Calendar calendar = new GregorianCalendar();
    Calendar calendar1 = new GregorianCalendar();
    for (int i = 1; i < list.size(); i += 2) {
      if (i + 1 < list.size()) {
        calendar.setTime(list.get(i));
        calendar1.setTime(list.get(i + 1));
        if (!((((calendar1.get(Calendar.HOUR)) - calendar.get(Calendar.HOUR)) >= 2)
            && (calendar.before(calendar1)))) {
          return false;
        }
      }
    }
    return isCorrect;
  }

  @Override
  public boolean filterSegmentClass(LinkedList<AnyFly> list) {
    boolean isCorrect = true;
    Calendar calendar = new GregorianCalendar();
    Calendar calendar1 = new GregorianCalendar();
    for (int i = 1; i < list.size(); i++) {
      if (i + 1 < list.size()) {
        calendar.setTime(list.get(i).getLanding());
        calendar1.setTime(list.get(i + 1).getStart());
        if (!((((calendar1.get(Calendar.HOUR)) - calendar.get(Calendar.HOUR)) >= 2)
                && (calendar.before(calendar1)))) {
          return false;
        }
      }
    }
    return isCorrect;
  }
}
