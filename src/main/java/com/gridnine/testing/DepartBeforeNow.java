package com.gridnine.testing;

import com.gridnine.testing.Flight;

import java.util.List;

public interface DepartBeforeNow {

    void checkIsFlightBeforeNow(List<Flight> flights);
}
