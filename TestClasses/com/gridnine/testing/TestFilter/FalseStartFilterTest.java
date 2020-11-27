package com.gridnine.testing.TestFilter;

import com.gridnine.testing.FilterFlyAny.FalseStartFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import junit.framework.TestCase;
import org.junit.Test;

public class FalseStartFilterTest extends TestCase {
  LinkedList<Date> list = new LinkedList<>();
  FalseStartFilter falseStartFilter = new FalseStartFilter();
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd.HH:mm");
  LinkedList<AnyFly> anyFlies = new LinkedList<>();

  @Override
  public void setUp() throws Exception {
    Date date;
    date = DATE_FORMAT.parse("2020-11-09.13:13");
    list.add(date);
    Date date1;
    date1 = DATE_FORMAT.parse("2020-11-09.15:13");
    list.add(date1);
    Date date2;
    date2 = DATE_FORMAT.parse("2020-11-09.17:13");
    list.add(date2);
    Date date3;
    date3 = DATE_FORMAT.parse("2020-11-09.19:13");
    list.add(date3);
    anyFlies.add(new AnyFly("2020-11-09.13:13","2020-11-09.15:13"));
    anyFlies.add(new AnyFly("2020-11-09.17:13","2020-11-09.19:13"));

  }


  @Test
  public void testFilterSegment() {
    boolean actual = falseStartFilter.filterSegment(list);
    assertTrue(actual);

  }
  @Test
  public void testFilterSegmentClsss() {
    boolean actual = falseStartFilter.filterSegmentClass(anyFlies);
    assertTrue(actual);

  }
}