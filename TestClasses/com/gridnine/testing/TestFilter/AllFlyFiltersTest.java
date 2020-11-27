package com.gridnine.testing.TestFilter;

import com.gridnine.testing.FilterFlyAny.AllFlyFilters;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import junit.framework.TestCase;
import org.junit.Test;

public class AllFlyFiltersTest extends TestCase {
  LinkedList<Date> list = new LinkedList<>();
  AllFlyFilters allFlyFilters = new AllFlyFilters();
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd.HH:mm");

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
  }
  @Test
  public void testFilterSegment() {
    boolean actual = allFlyFilters.filterSegment(list);
    assertTrue(actual);
    int size = list.size();
    assertEquals(size,4);

  }
}