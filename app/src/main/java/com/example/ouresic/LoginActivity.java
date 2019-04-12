package com.example.ouresic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public  class LoginActivity extends AppCompatActivity {
    EditText editText;
    Button buttonAdd,next;
    ListView listViewArtists;
    public String search;
    DatabaseReference databaseArtist;
    List<Artist> artistList;
    Query query ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonAdd = findViewById(R.id.ButtonAddartist);
        next=findViewById(R.id.NEXT);
        databaseArtist = FirebaseDatabase.getInstance().getReference("ARTISTS");

        editText=findViewById(R.id.editTextName2);

        listViewArtists = findViewById(R.id.listViewArtists);
        artistList = new ArrayList<>();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search=editText.getText().toString().trim();
                query= FirebaseDatabase.getInstance().getReference("ARTISTS")
                        .orderByChild("artistId").equalTo(search);
                query.addListenerForSingleValueEvent(valueEventListener);
            }

        });



    }


    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist=artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                final ArtistList adapter=new ArtistList(LoginActivity.this,artistList);
                listViewArtists.setAdapter(adapter);
              next.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent= new Intent(getBaseContext(),OptionsActivity.class);
                      startActivity(intent);
                  }
              });

            }
            else{
                Toast.makeText(LoginActivity.this, "IP NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
