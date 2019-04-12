package com.example.ouresic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Implicit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        Button a;
        a=findViewById(R.id.ch);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a= new Intent();
                a.setAction(Intent.ACTION_VIEW);
                a.setData(Uri.parse("https://www.google.com/maps/search/near+by+hospital/@10.0571901,76.4302243,13z/data=!3m1!4b1"));
                startActivity(a);

            }
        });

    }
}
