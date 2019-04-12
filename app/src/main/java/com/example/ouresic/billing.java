package com.example.ouresic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class billing extends AppCompatActivity {
    DatabaseReference databaseArtist;
EditText ipno,cost;
Button submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.billing);
        ipno=findViewById(R.id.ipno2);
        cost=findViewById(R.id.cost);
        databaseArtist= FirebaseDatabase.getInstance().getReference("BILLING");
        submit=findViewById(R.id.submit2);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=ipno.getText().toString().trim();
                String genre=cost.getText().toString().trim();
                String id=databaseArtist.push().getKey();
                Artist artist=new Artist(id,name,genre);
                databaseArtist.child(id).setValue(artist);
            }
        });
    }
}
