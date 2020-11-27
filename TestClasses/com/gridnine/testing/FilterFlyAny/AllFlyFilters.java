package com.gridnine.testing.FilterFlyAny;

import com.gridnine.testing.TestFilter.AnyFly;

import java.util.Date;
import java.util.LinkedList;

public class AllFlyFilters implements FilterFly,FilterFlyClass{
/**
 * Запускаем все фильтры.
 * */
  @Override
  public boolean filterSegment(LinkedList<Date> dates) {
    FalseStartFilter falseStartFilter = new FalseStartFilter();
    TimeFilterFly timeFilterFly = new TimeFilterFly();
    TimeOfDepartureFilter timeOfDepartureFilter = new TimeOfDepartureFilter();

    return (falseStartFilter.filterSegment(dates)) && (timeFilterFly.filterSegment(dates))
            && timeOfDepartureFilter.filterSegment(dates);
  }

  @Override
  public boolean filterSegmentClass(LinkedList<AnyFly> dates) {
    FalseStartFilter falseStartFilter = new FalseStartFilter();
    TimeFilterFly timeFilterFly = new TimeFilterFly();
    TimeOfDepartureFilter timeOfDepartureFilter = new TimeOfDepartureFilter();

    return (falseStartFilter.filterSegmentClass(dates)) && (timeFilterFly.filterSegmentClass(dates))
            && timeOfDepartureFilter.filterSegmentClass(dates);
  }
}
