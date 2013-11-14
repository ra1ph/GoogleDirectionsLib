package com.example.GoogleDirectionsLib;

import com.google.android.gms.maps.model.LatLng;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ra1ph
 * Date: 12.11.13
 * Time: 17:09
 */
public class URLCreator {
    public static String getUrl(ArrayList<String> points) {
        String str_origin = "origin=" + codeName(points.get(0));

        // Destination of route
        String str_dest = "destination=" + codeName(points.get(points.size() - 1));

        // Sensor enabled
        String sensor = "sensor=false";

        // Waypoints
        String waypoints = "";

        if (points.size() > 2)
            waypoints = "waypoints=";

        for (int i=1; i < points.size() - 1; i++) {
            String point = points.get(i);
            waypoints += codeName(point) + "|";
        }

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + waypoints;

        // Output format
        String output = "json";


        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    private static String codeName(String s) {
        String urlEncoded = null;
        try {
            urlEncoded = URLEncoder.encode(s.replaceAll(" ", "%20"), "UTF-8");
            //urlEncoded = output + "?" + parameters;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return urlEncoded;
    }
}
