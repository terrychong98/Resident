package com.example.terry.resident;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CarTagRegistration extends AppCompatActivity {
    DatabaseReference databaseResident;
    EditText editVehicleColor;
    EditText editVehicleModel;
    EditText editVehiclePlate;
    RadioGroup groupPeriod;
    RadioGroup groupType;
    TextView textAmount;
    TextView btnApproval;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_tag_register);
        SharedPreferences sharedPreferences = getSharedPreferences("username",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("TEXT","");
         databaseResident = FirebaseDatabase.getInstance().getReference("Resident/"+user);
         groupType =findViewById(R.id.groupType);
         groupPeriod = findViewById(R.id.groupPeriod);
         editVehiclePlate = findViewById(R.id.editVehiclePlate);
         editVehicleModel = findViewById(R.id.editVehicleModel);
         editVehicleColor = findViewById(R.id.editVehicleColor);
         textAmount = findViewById(R.id.textAmount);
         btnApproval = findViewById(R.id.btnApproval);
         groupType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(RadioGroup group, int checkedId) {
                 double totalPrice=0;
                 double price;
                 int rg1 = groupType.getCheckedRadioButtonId();
                 int rg2 = groupPeriod.getCheckedRadioButtonId();
                 RadioButton selectedVehicle = findViewById(rg1);
                 RadioButton selectedPeriod = findViewById(rg2);
                 String stickerPeriod = selectedPeriod.getText().toString();
                 String vehicleType = selectedVehicle.getText().toString();
                 if(vehicleType.equals("Car"))
                 {
                     price = 50;
                     if(stickerPeriod.equals("1 Year"))
                     {
                         totalPrice = price;
                     }
                     else if(stickerPeriod.equals("2 Year"))
                     {
                         totalPrice = price*2;
                     }
                     else if(stickerPeriod.equals("3 Year"))
                     {
                         totalPrice = price*3;
                     }
                 }
                 else if(vehicleType.equals("Motorcycle"))
                 {
                     price = 30;
                     if(stickerPeriod.equals("1 Year"))
                     {
                         totalPrice = price;
                     }
                     else if(stickerPeriod.equals("2 Year"))
                     {
                         totalPrice = price*2;
                     }
                     else if(stickerPeriod.equals("3 Year"))
                     {
                         totalPrice = price*3;
                     }
                 }

                 textAmount.setText(String.format("%.2f",totalPrice));
             }
         });
        groupPeriod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                double totalPrice=0;
                double price;
                int rg1 = groupType.getCheckedRadioButtonId();
                int rg2 = groupPeriod.getCheckedRadioButtonId();
                RadioButton selectedVehicle = findViewById(rg1);
                RadioButton selectedPeriod = findViewById(rg2);
                String stickerPeriod = selectedPeriod.getText().toString();
                String vehicleType = selectedVehicle.getText().toString();
                if(vehicleType.equals("Car")||vehicleType.equals("Kereta"))
                {
                    price = 50;
                    if(stickerPeriod.equals("1 Year")||stickerPeriod.equals("1 Tahun"))
                    {
                        totalPrice = price;
                    }
                    else if(stickerPeriod.equals("2 Year")||stickerPeriod.equals("2 Tahun"))
                    {
                        totalPrice = price*2;
                    }
                    else if(stickerPeriod.equals("3 Year")||stickerPeriod.equals("3 Tahun"))
                    {
                        totalPrice = price*3;
                    }
                }
                else if(vehicleType.equals("Motorcycle")||vehicleType.equals("Motosikal"))
                {
                    price = 30;
                    if(stickerPeriod.equals("1 Year")||stickerPeriod.equals("1 Tahun"))
                    {
                        totalPrice = price;
                    }
                    else if(stickerPeriod.equals("2 Year")||stickerPeriod.equals("2 Tahun"))
                    {
                        totalPrice = price*2;
                    }
                    else if(stickerPeriod.equals("3 Year")||stickerPeriod.equals("3 Tahun"))
                    {
                        totalPrice = price*3;
                    }
                }

                textAmount.setText(String.format("%.2f",totalPrice));
            }
        });

    }
    public void registerVehicle(View view)
    {
        double totalPrice=0;
        double price;
        int rg1 = groupType.getCheckedRadioButtonId();
        int rg2 = groupPeriod.getCheckedRadioButtonId();
        RadioButton selectedVehicle = findViewById(rg1);
        RadioButton selectedPeriod = findViewById(rg2);
        String vehicleType = selectedVehicle.getText().toString();
        String stickerPeriod = selectedPeriod.getText().toString();
        String vehiclePlate = editVehiclePlate.getText().toString().trim();
        String vehicleModel = editVehicleModel.getText().toString().trim();
        String vehicleColor = editVehicleColor.getText().toString().trim();
        if(vehiclePlate.isEmpty()||vehicleModel.isEmpty()||vehicleColor.isEmpty())
        {
            Toast.makeText(this,"Please fill in all the details",Toast.LENGTH_LONG).show();
        }
        else{
        if(vehicleType.equals("Car")||vehicleType.equals("Kereta"))
        {
            price = 50;
            if(stickerPeriod.equals("1 Year")||stickerPeriod.equals("1 Tahun"))
            {
                totalPrice = price;
            }
            else if(stickerPeriod.equals("2 Year")||stickerPeriod.equals("2 Tahun"))
            {
                totalPrice = price*2;
            }
            else if(stickerPeriod.equals("3 Year")||stickerPeriod.equals("3 Tahun"))
            {
                totalPrice = price*3;
            }
        }
        else if(vehicleType.equals("Motorcycle")||vehicleType.equals("Motosikal"))
        {
            price = 30;
            if(stickerPeriod.equals("1 Year")||stickerPeriod.equals("1 Tahun"))
            {
                totalPrice = price;
            }
            else if(stickerPeriod.equals("2 Year")||stickerPeriod.equals("2 Tahun"))
            {
                totalPrice = price*2;
            }
            else if(stickerPeriod.equals("3 Year")||stickerPeriod.equals("3 Tahun"))
            {
                totalPrice = price*3;
            }
        }
        String billID = databaseResident.push().getKey();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        CarDetails carDetails = new CarDetails(vehicleType,vehicleColor,vehiclePlate,vehicleModel,stickerPeriod,totalPrice,"Pending");
        BillDetails billDetails = new BillDetails(billID,"Vehicle Sticker , "+stickerPeriod,reportDate,totalPrice,vehiclePlate,vehicleType);
        databaseResident.child("Vehicle").child(vehiclePlate).setValue(carDetails);
        databaseResident.child("Bills").child("Pending").child(vehiclePlate).setValue(billDetails);
        Toast.makeText(this,"Successful Registered",Toast.LENGTH_LONG).show();
        finish();
        }
    }
    public void check_Approval(View view)
    {
        Intent intent = new Intent(this, ApprovalStatus.class);
        startActivity(intent);
    }
}
