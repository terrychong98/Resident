package com.example.terry.resident;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Wallet extends AppCompatActivity {
    Double accountBalance;
    TextView textViewWallet;
    EditText amountReload;
    Button btn20, btn50,btn100,btnReload;
    DatabaseReference databaseResident,databaseWallet;
    String user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        SharedPreferences sharedPreferences = getSharedPreferences("username",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("TEXT","");
        databaseResident = FirebaseDatabase.getInstance().getReference("Resident/"+user);
        databaseWallet = FirebaseDatabase.getInstance().getReference("Resident/"+user);
        textViewWallet = findViewById(R.id.textViewWallet);
        amountReload = findViewById(R.id.amountReload);
        btnReload=findViewById(R.id.btnReload);
        btn20 = findViewById(R.id.btn20);
        btn50 = findViewById(R.id.btn50);
        btn100 = findViewById(R.id.btn100);
        amountReload.clearFocus();
        btn20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountReload.setText("20");
            }
        });
        btn50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountReload.setText("50");
            }
        });
        btn100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountReload.setText("100");
            }
        });
        databaseWallet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                accountBalance = dataSnapshot.child("walletBalance").getValue(Double.class);
                textViewWallet.setText(String.format("RM %.2f",accountBalance));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount= amountReload.getText().toString();
                AlertDialog show = new AlertDialog.Builder(Wallet.this)
                        .setTitle("Reload")
                        .setMessage("Do you really want to reload RM "+amount+" ?")
                        //.setIcon(android.R.drawable)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                Double finalAmount = Double.parseDouble(amountReload.getText().toString());
                                String reloadID = databaseResident.push().getKey();
                                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                                Date today = Calendar.getInstance().getTime();
                                String reloadDate = df.format(today);
                                TransactionDetails transactionDetails = new TransactionDetails("Reload",reloadDate,finalAmount);
                                Toast.makeText(Wallet.this, "Reloaded", Toast.LENGTH_SHORT).show();
                                amountReload.setText("");
                                accountBalance = accountBalance+finalAmount;
                                databaseResident.child("Transaction").child(reloadID).setValue(transactionDetails);
                                databaseResident.child("walletBalance").setValue(accountBalance);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
    public void checkTransaction(View view)
    {
        Intent intent = new Intent(this, CheckTransaction.class);
        startActivity(intent);
    }
}
