package com.example.terry.resident;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Comparator;

public class ApprovalStatus extends AppCompatActivity {
    DatabaseReference databaseResident,databaseBill;
    RecyclerView recyclerView2;
    ArrayList<CarDetails> list;
    VehicleAdapter adapter;
    double accountBalance;
    double balanceLeft=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.approval_status);
        recyclerView2 = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sharedPreferences = getSharedPreferences("username",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("TEXT","");

        databaseResident = FirebaseDatabase.getInstance().getReference("/Resident/"+user+"/Vehicle");
        databaseBill = FirebaseDatabase.getInstance().getReference("/Resident/"+user+"/Bills/Pending");
        databaseResident.addValueEventListener(new ValueEventListener() {
            public final void deleteRecord(int position)
            {
                String carPlate = list.get(position).getVehiclePlate();
                databaseResident.child(carPlate).removeValue();
                databaseBill.child(carPlate).removeValue();
            }
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<CarDetails>();

                for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                {
                    CarDetails b = postSnapShot.getValue(CarDetails.class);
                    list.add(b);
                }
                adapter = new VehicleAdapter(ApprovalStatus.this,list);
                recyclerView2.setAdapter(adapter);
                adapter.setOnItemClickListener(new VehicleAdapter.OnItemClickListener() {
                    @Override
                    public void onDeleteClick(int position) {
                        deleteRecord(position);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ApprovalStatus.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
