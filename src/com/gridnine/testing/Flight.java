package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean that represents a flight.
 */
public class Flight {

    private final List<Segment> segments;
    private final int flightNumber;

    Flight(final List<Segment> segs, int flightNumber) {
        segments = segs;
        this.flightNumber = flightNumber;
    }

    List<Segment> getSegments() {
        return segments;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
                .collect(Collectors.joining(" "));
    }

}
