package com.example.GoogleDirectionsLib;

import android.widget.TimePicker;
import com.example.GoogleDirectionsLib.async.DownloadTask;
import com.example.GoogleDirectionsLib.data.Leg;
import com.example.GoogleDirectionsLib.data.Route;
import com.example.GoogleDirectionsLib.json.RoutesParser;
import com.example.GoogleDirectionsLib.listeners.BaseListener;
import com.example.GoogleDirectionsLib.listeners.OnGetDistanceListener;
import com.example.GoogleDirectionsLib.listeners.OnGetTimeDistanceListener;
import com.example.GoogleDirectionsLib.listeners.OnGetTimeListener;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ra1ph
 * Date: 12.11.13
 * Time: 17:09
 */
public class GoogleDirections {
    public void getTime(ArrayList<String> points,DirectionOption options,final OnGetTimeListener listener){
        String url = URLCreator.getUrl(points,options);

        DownloadTask downloadTask = new DownloadTask(new RoutesParser(),new BaseListener() {
            @Override
            public void onWorkDone(Object result) {
                if(result instanceof ArrayList){
                    ArrayList<Route> routes = (ArrayList<Route>) result;
                    long time = 0;
                    for(Route route : routes){
                        for(Leg leg : route.getLegs()){
                            time += leg.getDurationValue();
                        }
                    }
                    listener.onGetTimeListener(time);
                } else throw new ClassCastException();
            }
        });
        downloadTask.execute(url);
    }

    public void getDistance(ArrayList<String> points,DirectionOption options,final OnGetDistanceListener listener){
        String url = URLCreator.getUrl(points,options);

        DownloadTask downloadTask = new DownloadTask(new RoutesParser(),new BaseListener() {
            @Override
            public void onWorkDone(Object result) {
                if(result instanceof ArrayList){
                    ArrayList<Route> routes = (ArrayList<Route>) result;
                    long distance = 0;
                    for(Route route : routes){
                        for(Leg leg : route.getLegs()){
                            distance += leg.getDistanceValue();
                        }
                    }
                    listener.onGetDistanceListener(distance);
                } else throw new ClassCastException();
            }
        });
        downloadTask.execute(url);
    }

    public void getTimeDistance(LatLng current, ArrayList<String> points,DirectionOption options,final OnGetTimeDistanceListener listener){
        String url = URLCreator.getUrl(current,points,options);

        DownloadTask downloadTask = new DownloadTask(new RoutesParser(),new BaseListener() {
            @Override
            public void onWorkDone(Object result) {
                if(result instanceof ArrayList){
                    ArrayList<Route> routes = (ArrayList<Route>) result;
                    long distance = 0;
                    long time = 0;
                    for(Route route : routes){
                        for(Leg leg : route.getLegs()){
                            distance += leg.getDistanceValue();
                            time += leg.getDurationValue();
                        }
                    }
                    listener.onGetTimeDistanceListener(time, distance);
                } else throw new ClassCastException();
            }
        });
        downloadTask.execute(url);
    }
}
