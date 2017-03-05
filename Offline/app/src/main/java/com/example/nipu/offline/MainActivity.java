package com.example.nipu.offline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.osmdroid.util.GeoPoint;

import org.osmdroid.views.MapView;



import android.app.Activity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapView mapView = new MapView(this, 256); //constructor

        mapView.setClickable(true);

        mapView.setBuiltInZoomControls(true);

        setContentView(mapView); //displaying the MapView

        mapView.getController().setZoom(15); //set initial zoom-level, depends on your need

        mapView.getController().setCenter(new GeoPoint(52.221, 6.893)); //This point is in Enschede, Netherlands. You should select a point in your map or get it from user's location.

        mapView.setUseDataConnection(false); //keeps the mapView from loading online tiles using network connection.

    }
}
