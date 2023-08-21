package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FlightFilterImplTest {

    private final FlightFilterImpl flightFilter = new FlightFilterImpl();
    private final List<Flight> flights = new ArrayList<>();
    public final List<Segment> segs = new ArrayList<>();

    @Test
    @DisplayName("Checking the method for receiving flights with departures before the current time")
    public void getFlightsWithDepartureBeforeTheCurrentTimeTest() {
        Segment segment = new Segment(LocalDateTime.now().minusDays(2), LocalDateTime.now());
        segs.add(segment);
        Flight flight = new Flight(segs, 1);
        flights.add(flight);
        Set<Flight> filteredFlights = flightFilter.getFlightsWithDepartureBeforeTheCurrentTime(flights);
        assertFalse(filteredFlights.isEmpty());
    }

    @Test
    @DisplayName("Checking the method for obtaining segments with the arrival date before the departure date")
    public void arrivalDateBeforeDepartureDateTest() {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(4));
        segs.add(segment1);
        segs.add(segment2);
        Flight flight = new Flight(segs, 1);
        flights.add(flight);
        Set<Flight> filteredFlight = flightFilter.arrivalDateBeforeDepartureDate(flights);
        assertFalse(filteredFlight.isEmpty());
    }

    @Test
    @DisplayName("Checking the method of obtaining a flight with more than two hours of ground time")
    public void moreThanTwoHoursGroundTimeTest() {
        Segment segment1 = new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        Segment segment2 = new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3));
        Segment segment3 = new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5));
        Segment segment4 = new Segment(LocalDateTime.now().plusHours(6), LocalDateTime.now().plusHours(7));
        Segment segment5 = new Segment(LocalDateTime.now().plusHours(8), LocalDateTime.now().plusHours(9));
        Segment segment6 = new Segment(LocalDateTime.now().plusHours(10), LocalDateTime.now().plusHours(11));
        segs.add(segment1);
        segs.add(segment2);
        segs.add(segment3);
        segs.add(segment4);
        segs.add(segment5);
        segs.add(segment6);
        Flight flight = new Flight(segs, 1);
        flights.add(flight);
        Set<Flight> filteredFlights = flightFilter.moreThanTwoHoursGroundTime(flights);
        assertFalse(filteredFlights.isEmpty());
    }
}
