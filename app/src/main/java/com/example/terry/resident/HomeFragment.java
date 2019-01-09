package com.example.terry.resident;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("username",Context.MODE_PRIVATE);
        final String user = sharedPreferences.getString("TEXT","");


        Button RegisterCar =(Button)view.findViewById(R.id.btnCarTag);
        Button btnBilling = (Button)view.findViewById(R.id.btnBilling);
        Button Reload = (Button)view.findViewById(R.id.btnReload);
        Button btnFeedback = (Button)view.findViewById(R.id.btnFeedback);
        Button btnemergency = view.findViewById(R.id.btnEmergency);
        Button btnSupport = view.findViewById(R.id.btnSupport);

        btnemergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Emergency.class);
                startActivity(intent);
            }
        });
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Support.class);
                startActivity(intent);
            }
        });
        RegisterCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CarTagRegistration.class);
                startActivity(intent);
            }
        });
        btnBilling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ResidentBills.class);
                startActivity(intent);
            }
        });
        Reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Wallet.class);

                startActivity(intent);
            }
        });
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Feedback.class);
                startActivity(intent);
            }
        });
        return view;

    }

}
