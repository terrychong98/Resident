package com.example.terry.resident;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ResidentBills extends AppCompatActivity {

    DatabaseReference databaseResident,databaseWallet,databasePaid;
    RecyclerView recyclerView;
    ArrayList<BillDetails> list;
    BillAdapter adapter;
    TextView textViewBalance;
    double accountBalance;
    double balanceLeft=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_recycler);
        textViewBalance = findViewById(R.id.textViewBalance);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sharedPreferences = getSharedPreferences("username",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("TEXT","");
        databaseResident = FirebaseDatabase.getInstance().getReference("/Resident/"+user+"/Bills/Pending");
        databasePaid = FirebaseDatabase.getInstance().getReference("/Resident/"+user+"/Bills");
        databaseWallet = FirebaseDatabase.getInstance().getReference("Resident/"+user);
        databaseWallet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                accountBalance = dataSnapshot.child("walletBalance").getValue(Double.class);
                textViewBalance.setText(String.format("Balance : RM %.2f",accountBalance));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseResident.addValueEventListener(new ValueEventListener() {

          public void makePayment(final int position, String text)
          {
              DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
              Date today = Calendar.getInstance().getTime();
              String reportDate = df.format(today);
              balanceLeft = list.get(position).billPayment(accountBalance);
              final String billID = list.get(position).getBillID();
              final double billAmount = list.get(position).getBillAmount();
              final double amount = billAmount;
              final String billDesc = list.get(position).getBillDetails();
              final String vehicleType = list.get(position).getVehicleType();
              final String carPlate = list.get(position).getCarPlate();
              final String billDate = reportDate;
              final String date = reportDate;
              final BillDetails billDetails = new BillDetails(billID,billDesc,billDate,billAmount,carPlate,vehicleType);
              final TransactionDetails transDetails = new TransactionDetails("Pay Bill",date,amount);
              if(balanceLeft>0)
              {
                  AlertDialog show = new AlertDialog.Builder(ResidentBills.this)
                          .setTitle("Bills")
                          .setMessage("Do you really want to pay for this bill ?")
                          //.setIcon(android.R.drawable)
                          .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                              public void onClick(DialogInterface dialog, int whichButton) {
                                  accountBalance=balanceLeft;
                                  databaseWallet.child("walletBalance").setValue(accountBalance);

                                  databasePaid.child("Paid").child(billID).setValue(billDetails);
                                  databaseResident.child(carPlate).removeValue();
                                  databaseWallet.child("Vehicle").child(carPlate).child("approvalStatus").setValue("Approved");
                                  databaseWallet.child("Transaction").child(carPlate).setValue(transDetails);

                                      Toast.makeText(ResidentBills.this, "Paid", Toast.LENGTH_SHORT).show();
                              }
                          }).setNegativeButton(android.R.string.no, null).show();
              }
              else
              {
                  Toast.makeText(ResidentBills.this, "Not enough balance in wallet", Toast.LENGTH_SHORT).show();
              }
              adapter.notifyItemChanged(position);
          }

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list = new ArrayList<BillDetails>();

                for(DataSnapshot postSnapShot:dataSnapshot.getChildren())
                {
                    BillDetails b = postSnapShot.getValue(BillDetails.class);
                    list.add(b);
                }
                adapter = new BillAdapter(ResidentBills.this,list);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new BillAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        makePayment(position,"Clicked");
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ResidentBills.this, "Opsss.... Something is wrong", Toast.LENGTH_LONG).show();
            }
            });
        }

}
