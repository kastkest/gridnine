package com.gridnine.testing;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Filter implements DepartBeforeNow, ArriveBeforeDepart, TooMuchGroundTime {


    @Override
    public void checkIsFlightBeforeNow(List<Flight> flights) {
        List<Flight> cloneFlights = new ArrayList<>(flights);
        for (int i = 0; i < flights.size(); i++) {
            List<Segment> segments = flights.get(i).getSegments();
            for (int j = 0; j < segments.size(); j++) {
                if (segments.get(j).getDepartureDate().isBefore(LocalDateTime.now())) {
                    cloneFlights.remove(i);
                }
            }
        }
        System.out.println("Вылеты после текущего времени: " + cloneFlights);
    }

    @Override
    public void checkIsArriveBeforeDepart(List<Flight> flights) {
        List<Flight> cloneFlights = new ArrayList<>(flights);
        for (int i = 0; i < flights.size(); i++) {
            List<Segment> segments = flights.get(i).getSegments();
            for (int j = 0; j < segments.size(); j++) {
                if (segments.get(j).getArrivalDate().isBefore(segments.get(j).getDepartureDate())) {
                    cloneFlights.remove(i);
                }
            }
        }
        System.out.println("Вылеты с датой отлета позже даты прилета: " + cloneFlights);

    }

    @Override
    public void checkTimeOnTheGround(List<Flight> flights) {
        List<Flight> cloneFlights = new ArrayList<>(flights);
        List<Flight> longFlights = new ArrayList<>();
        for (int i = 0; i < flights.size(); i++) {
            List<Segment> segments = flights.get(i).getSegments();
            int totalDuration = 0;
            for (int j = 0; j < segments.size(); j++) {
                if ((j + 1) < segments.size()) {
                    Duration duration = Duration.between(segments.get(j).getArrivalDate(), segments.get(j + 1).getDepartureDate());
                    totalDuration += duration.toHours();
                    if (totalDuration > 2) {
                        longFlights.add(flights.get(i));
                    }
                }
            }
        }
        cloneFlights.removeAll(longFlights);
        System.out.println("Вылеты, общее время на земле которых не превышает 2-х часов: " + cloneFlights);
    }
}



