package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterImpl flightFilter = new FlightFilterImpl();

        System.out.println("Get all flights:");
        flightFilter.getAllFlights(flights);
        System.out.println("________________________________________" + "\n");

        System.out.println("Flights with departure before the current time:");
        flightFilter.getFlightsWithDepartureBeforeTheCurrentTime(flights);
        System.out.println("________________________________________" + "\n");

        System.out.println("Flights with segments with arrival date before departure date:");
        flightFilter.arrivalDateBeforeDepartureDate(flights);
        System.out.println("________________________________________" + "\n");

        System.out.println("Flights with more than two hours ground time:");
        flightFilter.moreThanTwoHoursGroundTime(flights);
        System.out.println("________________________________________" + "\n");

    }
}
