package com.gridnine.testing;

import java.util.List;
import java.util.Set;

public interface FlightFilter {

    void getAllFlights (List<Flight> flights);

    Set<Flight> getFlightsWithDepartureBeforeTheCurrentTime (List<Flight> flights);

    Set<Flight> arrivalDateBeforeDepartureDate (List<Flight> flights);

    Set<Flight> moreThanTwoHoursGroundTime (List<Flight> flights);
}
