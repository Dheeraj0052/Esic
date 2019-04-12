package com.example.ouresic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public  class OptionsActivity extends AppCompatActivity {
    Button book;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_options);

        Button  btnsymptom, btnappoint , btnconsult ,btnemer;
        btnsymptom =findViewById(R.id.btnsym);
        btnappoint=findViewById(R.id.btnonline);
        btnconsult=findViewById(R.id.btnconsult);
        book=findViewById(R.id.book);
        btnemer=findViewById(R.id.btnemer);

        btnsymptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),SympActivity.class);
                startActivity(intent);
            }
        });

        btnappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getBaseContext(),OnlineActivity.class);
                startActivity(intent);
            }
        });

        btnconsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getBaseContext(),Contact.class);
                startActivity(intent);
            }
        });

        btnemer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getBaseContext(),Implicit.class);
                startActivity(intent);

            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),bookedappontment.class);
                startActivity(intent);
            }
        });
    }
}
