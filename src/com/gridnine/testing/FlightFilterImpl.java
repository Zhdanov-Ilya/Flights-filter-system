package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlightFilterImpl implements FlightFilter {

    Set<Flight> flightSet = new HashSet<>();
    List<Segment> segs = new ArrayList<>();
    DateTimeFormatter formatter = getDateFormatter();

    @Override
    public void getAllFlights (List<Flight> flights) {
        for (Flight fl : flights) {
            System.out.println("Flight number: " + fl.getFlightNumber());
            for (int i = 0; i < fl.getSegments().size(); i++) {
                System.out.println(fl.getSegments().get(i));
            }
        }
    }

    @Override
    public Set<Flight> getFlightsWithDepartureBeforeTheCurrentTime(List<Flight> flights) {

        for (Flight fl : flights) {

            LocalDateTime dep = fl.getSegments().get(0).getDepartureDate();
            LocalDateTime arr = fl.getSegments().get(fl.getSegments().size() - 1).getArrivalDate();

            if (dep.isBefore(LocalDateTime.now())) {
                showFlight(fl, dep, arr);
                flightSet.add(fl);
            }
        }
        return flightSet;
    }

    @Override
    public Set<Flight> arrivalDateBeforeDepartureDate(List<Flight> flights) {

        for (Flight fl : flights) {

            if (fl.getSegments().size() >= 2) {

                for (int i = 1; i < fl.getSegments().size(); i++) {

                    LocalDateTime dep = fl.getSegments().get(i).getDepartureDate();
                    LocalDateTime arr = fl.getSegments().get(i - 1).getArrivalDate();

                    if (arr.isBefore(dep)) {
                        showFlight(fl, dep, arr);
                        flightSet.add(fl);
                        break;
                    }
                }
            }
        }
        return flightSet;
    }

    @Override
    public Set<Flight> moreThanTwoHoursGroundTime(List<Flight> flights) {

        for (Flight fl: flights) {

            long groundTime = 0;

            if (fl.getSegments().size() == 2) {
                LocalDateTime arr = fl.getSegments().get(0).getArrivalDate();
                LocalDateTime dep = fl.getSegments().get(1).getDepartureDate();

                if (arr.plusHours(2).isBefore(dep)) {
                    showFlight(fl, dep, arr);
                    flightSet.add(fl);
                }
            }

            if (fl.getSegments().size() > 2) {

                for (int i = 1; i < fl.getSegments().size(); i++) {

                    LocalDateTime arr = fl.getSegments().get(i - 1).getArrivalDate();
                    LocalDateTime dep = fl.getSegments().get(i).getDepartureDate();
                    groundTime += Duration.between(arr, dep).toHours();
                }

                LocalDateTime dep = fl.getSegments().get(0).getDepartureDate();
                LocalDateTime arr = fl.getSegments().get(fl.getSegments().size() - 1).getArrivalDate();

                if (groundTime > 2) {
                    showFlight(fl, dep, arr);
                    flightSet.add(fl);
                }
            }
        }
        return flightSet;
    }

    private void showFlight (Flight fl, LocalDateTime dep, LocalDateTime arr) {
        System.out.println("Flight number: " + fl.getFlightNumber() + "\n" + "Departure Time: " + formatter.format(dep)
                + "\n" + "Arrival time: " + formatter.format(arr) + "\n");
    }

    private DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    }
}