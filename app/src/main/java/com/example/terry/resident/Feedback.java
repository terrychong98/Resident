package com.example.terry.resident;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Feedback extends AppCompatActivity {
    DatabaseReference databaseResident;
    EditText editTextFeedback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        SharedPreferences sharedPreferences = getSharedPreferences("username",Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("TEXT","");
        databaseResident = FirebaseDatabase.getInstance().getReference("Resident/"+user+"/Feedback");
        editTextFeedback = findViewById(R.id.editTextFeedback);

    }
    public void sendFeedBack(View view)
    {
        String content = editTextFeedback.getText().toString().trim();
        if(content.isEmpty())
        {
            Toast.makeText(this,"Please fill in all the details",Toast.LENGTH_LONG).show();
        }
        else
        {
            String feedbackID = databaseResident.push().getKey();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date today = Calendar.getInstance().getTime();
            String reportDate = df.format(today);
            FeedBackDetails details = new FeedBackDetails(feedbackID,content,reportDate);
            databaseResident.child(feedbackID).setValue(details);
            Toast.makeText(this,"Sent",Toast.LENGTH_LONG).show();
            finish();

        }
    }
}
