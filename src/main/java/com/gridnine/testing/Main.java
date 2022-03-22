package com.gridnine.testing;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println(flights);
        Filter filter = new Filter();
        check(Filter.class, filter, flights);
    }

    public static void check(Class clazz, Filter filter, List<Flight> flights) {
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            try {
                method.invoke(filter, flights);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
