package com.example.gameandroidassignment1.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.gameandroidassignment1.R;
import com.example.gameandroidassignment1.assets.CallBack_LocationProtocol;
import com.example.gameandroidassignment1.assets.MySPV3;
import com.example.gameandroidassignment1.fragments.Fragment_List;
import com.example.gameandroidassignment1.fragments.MapsFragment;
import com.example.gameandroidassignment1.logic.Result;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

public class LeaderboardActivity extends AppCompatActivity {
    public static final String KEY_SCORE = "KEY_SCORE";

    private FrameLayout panel_LAY_list;
    private FrameLayout panel_LAY_map;

    private Fragment_List fragment_list;
    private MapsFragment fragment_map;

    Location location;
    int lastScore;

    CallBack_LocationProtocol callBack_userProtocol = new CallBack_LocationProtocol() {
        @Override
        public void getLocation(LatLng location) {
            fragment_map.setMarker(location);
//            Toast.makeText(this, "Altitude: " + location.latitude + "\nLongitude: " + location.longitude, Toast.LENGTH_LONG).show();
//            Toast.makeText(this, "A", Toast.LENGTH_LONG).show();
//            System.err.println("Altitude: " + location.latitude + "\nLongitude: " + location.longitude);
        }
    };

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        MySPV3.init(this);

        Intent previousIntent = getIntent();
        lastScore = previousIntent.getExtras().getInt(KEY_SCORE);

        fragment_list = new Fragment_List();
        fragment_map = new MapsFragment();

        fragment_list.setCallBack_userProtocol(callBack_userProtocol);

        if(lastScore != -1) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_DENIED)
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            else {
                FusedLocationProviderClient fusedLocationProviderClient;
                fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                    location = task.getResult();
                    fragment_list.addResult(new Result().
                            setScore(lastScore).
                            setLatitude(location.getLatitude()).
                            setLongitude(location.getLongitude()));
//                fragment_map.setMarker(new LatLng(location.getLatitude(), location.getLongitude()));
//                addResult(new Result().setScore(lastScore).setLocation(location));
//                Toast toast = Toast.makeText(this, "Altitude: " + location.getAltitude() + "\nLongitude: " + location.getLongitude(), Toast.LENGTH_LONG);
//                toast.show();
                });
            }
        }
//        fragment_list.setLastScore(lastScore);
//        while(location == null) {}

//        fragment_list.addResult(new Result().setScore(lastScore).setLocation(location));

        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_list, fragment_list).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_map, fragment_map).commit();


//        fragment_map.setLocations(fragment_list.getLeaderboardLocations());
    }

    @Override
    protected void onResume() {
        super.onResume();

//        fragment_list.addResult(new Result().setScore(lastScore).setLocation(location));
//        fragment_map.setLocations(fragment_list.getLeaderboardLocations());
    }

    @SuppressLint("MissingPermission")
    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    FusedLocationProviderClient fusedLocationProviderClient;
                    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

                    fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                        location = task.getResult();
                        fragment_list.addResult(new Result().
                                setScore(lastScore).
                                setLatitude(location.getLatitude()).
                                setLongitude(location.getLongitude()));
//                        fragment_map.setMarker(new LatLng(location.getLatitude(), location.getLongitude()));
//                        addResult(new Result().setScore(lastScore).setLocation(location));
//                        Toast toast = Toast.makeText(this, "Altitude: "+ location.getAltitude() +"\nLongitude: "+ location.getLongitude(), Toast.LENGTH_LONG);
//                        toast.show();
                    });
                } else {
                    //TODO
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            });
}
