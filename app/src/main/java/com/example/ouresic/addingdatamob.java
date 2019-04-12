package com.example.ouresic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
public class addingdatamob extends AppCompatActivity {

    Button sub;
    DatabaseReference databaseArtist;
    Query query;
    ListView listViewArtists;
    List<Artist> artistList;
    String genree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nextt);

        artistList = new ArrayList<>();
        listViewArtists = findViewById(R.id.listViewArtists8);
        databaseArtist = FirebaseDatabase.getInstance().getReference("IPMOB");
        sub = findViewById(R.id.me);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query = FirebaseDatabase.getInstance().getReference("IPMOB");
                query.addListenerForSingleValueEvent(valueEventListener);

            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            artistList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot artistSnapshot : dataSnapshot.getChildren()) {
                    Artist artist = artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);
                    genree = artist.getArtistGenre();
                    try {
                        // Construct data
                        String apiKey = "apikey=" + "Dq79ughjIwc-B2bO7ISEyHuE038t1VJfRuuZfXxoxv";
                        String message = "&message=" + "PLEASE BOOK YOUR APPOINTMENT FOR REGULAR CHECKUPS,TEAM ESIC";
                        String sender = "&sender=" + "TXTLCL";
                        String numbers = "&numbers=" + genree;

                        // Send data
                        HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                        String data = apiKey + numbers + message + sender;
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                        conn.getOutputStream().write(data.getBytes("UTF-8"));
                        final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        final StringBuffer stringBuffer = new StringBuffer();
                        String line;
                        while ((line = rd.readLine()) != null) {
                            // stringBuffer.append(line);
                            Toast.makeText(addingdatamob.this, "MESSASE is:"+line, Toast.LENGTH_LONG).show();
                        }
                        rd.close();

                        // return stringBuffer.toString();
                    } catch (Exception e) {
                        //System.out.println("Error SMS " + e);
                        //  return "Error " + e;
                        Toast.makeText(addingdatamob.this, "ERROR is:"+e, Toast.LENGTH_LONG).show();
                    }
                }
              //  final ArtistList adapter = new ArtistList(addingdatamob.this, artistList);
             //   listViewArtists.setAdapter(adapter);
                //
            } else {
                Toast.makeText(addingdatamob.this, "IP NOT FOUND", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


}
