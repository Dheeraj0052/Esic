package com.example.ouresic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class requirement extends AppCompatActivity {
   EditText name0,id0;
   Button sub;
    DatabaseReference databaseArtist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requirement);
        name0=findViewById(R.id.name);
        id0=findViewById(R.id.ID);
        sub=findViewById(R.id.button2);
        databaseArtist= FirebaseDatabase.getInstance().getReference("REQUIREMTS");
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name0.getText().toString().trim();
                String genre=id0.getText().toString();
                String id=databaseArtist.push().getKey();
                Artist artist=new Artist(id,name,genre);
                databaseArtist.child(id).setValue(artist);

            }
        });
    }
}
