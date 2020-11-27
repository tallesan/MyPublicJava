package com.gridnine.testing.TestFilter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnyFly {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd.HH:mm");
    Date start;
    Date landing;

    public AnyFly(String start, String landing) throws ParseException {
        this.start = DATE_FORMAT.parse(start);
        this.landing = DATE_FORMAT.parse(landing);
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getLanding() {
        return landing;
    }

    public void setLanding(Date landing) {
        this.landing = landing;
    }

    @Override
    public String toString() {
        return "AnyFly{" +
                "start=" + start +
                ", landing=" + landing +
                '}';
    }
}
