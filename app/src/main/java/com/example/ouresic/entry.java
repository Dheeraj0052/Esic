package com.example.ouresic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class entry extends AppCompatActivity {
    Calendar calender;
    SimpleDateFormat sdf;
    java.util.Date date;
    String lo;
    Button submit;
    EditText ip;
    DatabaseReference databaseArtist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        ip=findViewById(R.id.ipno);
        databaseArtist= FirebaseDatabase.getInstance().getReference("ENTRY");
        submit=findViewById(R.id.submit);
        calender=Calendar.getInstance();
        sdf=new SimpleDateFormat("dd-MM-yyyy");
        lo=sdf.format(calender.getTime());
        try {
            date=sdf.parse(lo);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=ip.getText().toString().trim();
                String genre=date.toString().trim();
                String id=databaseArtist.push().getKey();
                Artist artist=new Artist(id,name,genre);
                databaseArtist.child(id).setValue(artist);
            }
        });
    }
}
