package com.example.ouresic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OnlineActivity extends AppCompatActivity


{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);

        Button med,paed,ortho,skin;
        med=findViewById(R.id.med);
        paed=findViewById(R.id.pae);
        ortho=findViewById(R.id.ortho);
        skin=findViewById(R.id.skin);

        med.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a=new Intent(getBaseContext(),Date.class);
                startActivity(a);


            }
        });
        paed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b=new Intent(getBaseContext(),DatePaediatric.class);
                startActivity(b);
            }
        });

        ortho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c=new Intent(getBaseContext(),DateOrtho.class);
                startActivity(c);
            }
        });

        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d=new Intent(getBaseContext(),DateSkin.class);
                startActivity(d);
            }
        });
    }
}
