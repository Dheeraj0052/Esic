package com.example.ouresic;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.valueOf;

public class DateOrtho extends LogActivity {
    ListView listViewArtists;
    DatabaseReference databaseArtist2;
    List<Artist> artistList;
    Query query;
    EditText se;
    String id2;
    String s, search;
    EditText e;
    Button submit;
    DatabaseReference count;
    Calendar calender;
    SimpleDateFormat sdf;
    java.util.Date date,date2;
    String lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        Toolbar toolbar = findViewById(R.id.toolbar);
        listViewArtists = findViewById(R.id.listViewArtists);
        artistList = new ArrayList<>();
        se = findViewById(R.id.serch);
        calender=Calendar.getInstance();
        sdf=new SimpleDateFormat("dd-MM-yyyy");
        lo=sdf.format(calender.getTime());
        try {
            date=sdf.parse(lo);
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        count=FirebaseDatabase.getInstance().getReference("MEDICINECOUNT");
        databaseArtist2 = FirebaseDatabase.getInstance().getReference("ORTHO");
        id2 = databaseArtist2.push().getKey();
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        e = findViewById(R.id.r);

        submit = findViewById(R.id.sub);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s = e.getText().toString().trim();
                try {
                    date2=sdf.parse(s);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }

                search = se.getText().toString().trim();
                Query q=FirebaseDatabase.getInstance().getReference("ARTISTS")
                        .orderByChild("artistId").equalTo(search);
                q.addListenerForSingleValueEvent(valueEventListener2);

            }
        });



    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                ArtistList adapter = new ArtistList(DateOrtho.this, artistList);
                listViewArtists.setAdapter(adapter);
                Toast.makeText(DateOrtho.this, "Date Not AVAILABLE", Toast.LENGTH_SHORT).show();
            } else {
                Artist artist = new Artist(s, "ORTHO", search);
                databaseArtist2.child(id2).setValue(artist);
                Toast.makeText(DateOrtho.this, "APPOINTMENT BOOKED", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                if(date.equals(date2)||date.after(date2)) {
                    Toast.makeText(DateOrtho.this, "INVALID DATE", Toast.LENGTH_SHORT).show();
                }
                else{

                    query = FirebaseDatabase.getInstance().getReference("ORTHO")
                            .orderByChild("artistId").equalTo(s);
                    query.addListenerForSingleValueEvent(valueEventListener);
                }
            } else {
                Toast.makeText(DateOrtho.this, "IP NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
