package com.example.gameandroidassignment1.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.gameandroidassignment1.activities.LeaderboardActivity;
import com.example.gameandroidassignment1.assets.CallBack_LocationProtocol;
import com.example.gameandroidassignment1.logic.Leaderboard;
import com.example.gameandroidassignment1.assets.MySPV3;
import com.example.gameandroidassignment1.R;
import com.example.gameandroidassignment1.logic.Result;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.textview.MaterialTextView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Fragment_List extends Fragment {
    private static final String SP_KEY_LEADERBOARD = "SP_KEY_LEADERBOARD";

    private Leaderboard leaderboard = new Leaderboard();

    private LinearLayout[] rows = new LinearLayout[10];
    private MaterialTextView[] scores = new MaterialTextView[10];
    private MaterialTextView[] dates = new MaterialTextView[10];

//    private int lastScore;
//    private Location location;

    private CallBack_LocationProtocol callBack_locationProtocol;

    public void setCallBack_userProtocol(CallBack_LocationProtocol callBack_locationProtocol) {
        this.callBack_locationProtocol = callBack_locationProtocol;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard_list, container, false);

        findViews(view);

//        if(lastScore != -1) {
//            if(ContextCompat.checkSelfPermission(
//                    Fragment_List.this.getContext(),
//                    Manifest.permission.ACCESS_FINE_LOCATION) ==
//                    PackageManager.PERMISSION_GRANTED)
//                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
//            else {
//
//            }

//            fragment_list.addResult(new Result().setScore(lastScore).setLocation(location));
//        }

//        updateLeaderboard();

        fillList();

        initViews();

        return view;
    }

    public void addResult(Result result) {
        read();

        if(result.getScore() != -1) {
            leaderboard.addResult(result);

            write();
        }

        fillList();
    }

//    private void updateLeaderboard(){
//        read();
//
//        if(lastScore != -1) {
//            leaderboard.addResult(new Result(this.getContext())
//                    .setScore(lastScore));
//
//            write();
//        }
//    }

    private void read() {
        String s = MySPV3.getInstance().getString(SP_KEY_LEADERBOARD, null);
        leaderboard = new Gson().fromJson(s, Leaderboard.class);
        if(leaderboard == null)
            leaderboard = new Leaderboard();
    }

    private void write() {
        MySPV3.getInstance().putString(SP_KEY_LEADERBOARD, new Gson().toJson(leaderboard));
    }

    private void findViews(View view) {
        rows[0] = view.findViewById(R.id.leaderboardList_LAYOUT_row1);
        rows[1] = view.findViewById(R.id.leaderboardList_LAYOUT_row2);
        rows[2] = view.findViewById(R.id.leaderboardList_LAYOUT_row3);
        rows[3] = view.findViewById(R.id.leaderboardList_LAYOUT_row4);
        rows[4] = view.findViewById(R.id.leaderboardList_LAYOUT_row5);
        rows[5] = view.findViewById(R.id.leaderboardList_LAYOUT_row6);
        rows[6] = view.findViewById(R.id.leaderboardList_LAYOUT_row7);
        rows[7] = view.findViewById(R.id.leaderboardList_LAYOUT_row8);
        rows[8] = view.findViewById(R.id.leaderboardList_LAYOUT_row9);
        rows[9] = view.findViewById(R.id.leaderboardList_LAYOUT_row10);

        scores[0] = view.findViewById(R.id.leaderboardList_LBL_score1);
        scores[1] = view.findViewById(R.id.leaderboardList_LBL_score2);
        scores[2] = view.findViewById(R.id.leaderboardList_LBL_score3);
        scores[3] = view.findViewById(R.id.leaderboardList_LBL_score4);
        scores[4] = view.findViewById(R.id.leaderboardList_LBL_score5);
        scores[5] = view.findViewById(R.id.leaderboardList_LBL_score6);
        scores[6] = view.findViewById(R.id.leaderboardList_LBL_score7);
        scores[7] = view.findViewById(R.id.leaderboardList_LBL_score8);
        scores[8] = view.findViewById(R.id.leaderboardList_LBL_score9);
        scores[9] = view.findViewById(R.id.leaderboardList_LBL_score10);

        dates[0] = view.findViewById(R.id.leaderboardList_LBL_date1);
        dates[1] = view.findViewById(R.id.leaderboardList_LBL_date2);
        dates[2] = view.findViewById(R.id.leaderboardList_LBL_date3);
        dates[3] = view.findViewById(R.id.leaderboardList_LBL_date4);
        dates[4] = view.findViewById(R.id.leaderboardList_LBL_date5);
        dates[5] = view.findViewById(R.id.leaderboardList_LBL_date6);
        dates[6] = view.findViewById(R.id.leaderboardList_LBL_date7);
        dates[7] = view.findViewById(R.id.leaderboardList_LBL_date8);
        dates[8] = view.findViewById(R.id.leaderboardList_LBL_date9);
        dates[9] = view.findViewById(R.id.leaderboardList_LBL_date10);
    }

    private void initViews() {
        for(int i = 0; i < rows.length; i++) {
            int finalI = i;
            rows[i].setOnClickListener(view -> {
                if (callBack_locationProtocol != null) {
                    read();
                    for(Result r : leaderboard.getLeaderboard())
                        System.err.println(r);
                    callBack_locationProtocol.getLocation(leaderboard.getLeaderboard().get(finalI).getLocation());
                }
            });
        }
    }

    private void fillList() {
        read();

        for(int i = 0; i < leaderboard.getLeaderboard().size(); i++) {
            rows[i].setVisibility(View.VISIBLE);
            scores[i].setText(""+ leaderboard.getLeaderboard().get(i).getScore());
            dates[i].setText(""+ leaderboard.getLeaderboard().get(i).getDate());
        }
    }

//    public ArrayList<LatLng> getLeaderboardLocations() {
//        ArrayList<LatLng> locations = new ArrayList<>();
//
//        for(Result result : leaderboard.getLeaderboard()) {
//            locations.add(new LatLng(result.getLocation().getLatitude(), result.getLocation().getLongitude()));
//        }
//
//        return locations;
//    }

//    public void setLastScore(int lastScore) {
//        this.lastScore = lastScore;
//    }
}
