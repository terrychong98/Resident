package com.example.terry.resident;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CheckTransaction extends AppCompatActivity {

    DatabaseReference databaseReload;
    RecyclerView recyclerView;
    ArrayList<TransactionDetails> list;
    TransactionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_history);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sharedPreferences = getSharedPreferences("username",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("TEXT","");
        databaseReload =FirebaseDatabase.getInstance().getReference("/Resident/"+user+"/Transaction");

        databaseReload.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<TransactionDetails>();

                for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                {
                    TransactionDetails b = postSnapShot.getValue(TransactionDetails.class);
                    list.add(b);
                }
                adapter = new TransactionAdapter(CheckTransaction.this,list);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CheckTransaction.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
