package com.example.ouresic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class bookedappontment extends AppCompatActivity {
    EditText ip;
    Button medicine,paed,skin,ortho;
    ListView listViewArtists;
    List<Artist> artistList;
    String search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked);
        listViewArtists=findViewById(R.id.listViewbooked);
        artistList = new ArrayList<>();
        medicine=findViewById(R.id.med);
        paed=findViewById(R.id.pae);
        skin=findViewById(R.id.skin);
        ortho=findViewById(R.id.ortho);
        ip=findViewById(R.id.ip);

        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search=ip.getText().toString().trim();
                Query q=FirebaseDatabase.getInstance().getReference("ARTISTS")
                        .orderByChild("artistId").equalTo(search);
                q.addListenerForSingleValueEvent(valueEventListener2);

            }
        });
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search=ip.getText().toString().trim();
                Query q=FirebaseDatabase.getInstance().getReference("ARTISTS")
                        .orderByChild("artistId").equalTo(search);
                q.addListenerForSingleValueEvent(valueEventListener3);

            }
        });
        ortho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search=ip.getText().toString().trim();
                Query q=FirebaseDatabase.getInstance().getReference("ARTISTS")
                        .orderByChild("artistId").equalTo(search);
                q.addListenerForSingleValueEvent(valueEventListener4);

            }
        });
        paed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search=ip.getText().toString().trim();
                Query q=FirebaseDatabase.getInstance().getReference("ARTISTS")
                        .orderByChild("artistId").equalTo(search);
                q.addListenerForSingleValueEvent(valueEventListener5);

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
                ArtistList adapter = new ArtistList(bookedappontment.this, artistList);
                listViewArtists.setAdapter(adapter);
            }
            else {
                Toast.makeText(bookedappontment.this, "NOT FOUND ANY APPOINTMENT ON THIS DEPARTMENT", Toast.LENGTH_SHORT).show();

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
                 //   artistList.add(artist);

                }
                Query q2=FirebaseDatabase.getInstance().getReference("MEDICINE")
                        .orderByChild("artistGenre").equalTo(search);
                q2.addListenerForSingleValueEvent(valueEventListener);
            } else {
                Toast.makeText(bookedappontment.this, "IP NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener valueEventListener3 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);

                }


                Query q5=FirebaseDatabase.getInstance().getReference("SKIN")
                        .orderByChild("artistGenre").equalTo(search);
                q5.addListenerForSingleValueEvent(valueEventListener);
            } else {
                Toast.makeText(bookedappontment.this, "IP NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener valueEventListener4 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);
                }
                Query q5=FirebaseDatabase.getInstance().getReference("ORTHO")
                        .orderByChild("artistGenre").equalTo(search);
                q5.addListenerForSingleValueEvent(valueEventListener);
            } else {
                Toast.makeText(bookedappontment.this, "IP NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener valueEventListener5 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            if (dataSnapshot.exists()) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);

                }

                Query q4=FirebaseDatabase.getInstance().getReference("PAEDIATRIC")
                        .orderByChild("artistGenre").equalTo(search);
                q4.addListenerForSingleValueEvent(valueEventListener);

            } else {
                Toast.makeText(bookedappontment.this, "IP NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
