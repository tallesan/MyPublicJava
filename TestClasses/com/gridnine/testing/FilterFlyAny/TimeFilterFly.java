package com.gridnine.testing.FilterFlyAny;

import com.gridnine.testing.TestFilter.AnyFly;

import java.util.Date;
import java.util.LinkedList;

public class TimeFilterFly implements FilterFly, FilterFlyClass {
    /**
     * Время вылета и приземления корректны
     */

    @Override
    public boolean filterSegment(LinkedList<Date> list) {
        boolean isCorrect = true;
        for (int i = 0; i < list.size(); i += 2) {
            if (i + 1 < list.size()) {
                if (!list.get(i).before(list.get(i + 1))) {
                    isCorrect = false;
                }
            }
        }
        return isCorrect;
    }

    @Override
    public boolean filterSegmentClass(LinkedList<AnyFly> list) {
        return list.stream().noneMatch(anyFly -> anyFly.getStart().after(anyFly.getLanding()));
    }
}
