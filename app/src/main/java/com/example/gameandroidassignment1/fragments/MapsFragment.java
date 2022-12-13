package com.example.gameandroidassignment1.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gameandroidassignment1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsFragment extends Fragment {
    private ArrayList<LatLng> locations = new ArrayList<>();
    private GoogleMap googleMap;

    private final OnMapReadyCallback callback = googleMap -> {

        this.googleMap = googleMap;
//        LatLng sydney = new LatLng(-34, 151);

//        for(int i = 0; i < locations.size(); i++) {
//            googleMap.addMarker(new MarkerOptions().position(locations.get(i)).title("#"+(i+1)));
////                googleMap.moveCamera(CameraUpdateFactory.newLatLng());
//        }
    };

    @SuppressLint("MissingPermission")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    public void setMarker(LatLng location) {
        googleMap.clear();
        googleMap.addMarker(new MarkerOptions().position(location));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
    }

//    public void setLocations(ArrayList<LatLng> locations) {
//        this.locations = locations;
//    }
}