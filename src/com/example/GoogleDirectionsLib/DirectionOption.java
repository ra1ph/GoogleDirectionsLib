package com.example.GoogleDirectionsLib;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ra1ph
 * Date: 12.11.13
 * Time: 17:09
 *
 * Class for option route
 */
public class DirectionOption {
    public enum TravelMode{
        DRIVING, WALKING, BICYCLING, TRANSIT
    }

    public enum AvoidType{
        TOLLS,HIGHWAYS,NOT_STAND
    }

    public enum UnitSystem{
        METRIC,IMPERIAL
    }

    private TravelMode travelMode = TravelMode.DRIVING;
    private boolean isAlternatives = false;
    private AvoidType avoid = AvoidType.NOT_STAND;
    private UnitSystem unit = UnitSystem.METRIC;
    private String language = "en";
    private Date departureTime;
    private Date arrivalTime;


}
