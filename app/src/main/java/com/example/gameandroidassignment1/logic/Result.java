package com.example.gameandroidassignment1.logic;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.gameandroidassignment1.activities.GameActivity;
import com.example.gameandroidassignment1.activities.LeaderboardActivity;
import com.example.gameandroidassignment1.fragments.Fragment_List;
import com.example.gameandroidassignment1.fragments.MapsFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

public class Result {
    private int score = 0;
    private String date;
    private double latitude;
    private double longitude;

    public Result() {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,FormatStyle.SHORT);
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }

//    private final LocationListener mLocationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(final Location location) {
//            setLocation(new LatLng(location.getLatitude(), location.getLongitude()));
//        }
//    };

    public int getScore() {
        return score;
    }

    public Result setScore(int score) {
        this.score = score;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Result setDate(String date) {
        this.date = date;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Result setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Result setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public LatLng getLocation() {
        return new LatLng(this.latitude, this.longitude);
    }

    @Override
    public String toString() {
        return "Result{" +
                "score=" + score +
                ", date='" + date + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
