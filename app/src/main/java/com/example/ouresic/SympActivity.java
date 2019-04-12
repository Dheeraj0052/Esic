package com.example.ouresic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

@SuppressLint("Registered")
public class SympActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symp);

    Button fev, cold,dig,head,other;
    fev=findViewById(R.id.fev);
    cold=findViewById(R.id.cold);
    dig=findViewById(R.id.dig);
    head=findViewById(R.id.head);
    other=findViewById(R.id.other);

    fev.setOnClickListener(this);
    cold.setOnClickListener(this);
        dig.setOnClickListener(this);
        head.setOnClickListener(this);
        other.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId())
        { case R.id.fev:
            Intent i=new Intent(getBaseContext(),Fever.class);
            startActivity(i);
                break;

            case R.id.cold:
                Intent intent=new Intent(getBaseContext(),Cold.class);
                startActivity(intent);
                break;

            case R.id.dig:
                Intent in=new Intent(getBaseContext(),Dig.class);
                startActivity(in);
                break;

            case R.id.head:
                Intent a=new Intent(getBaseContext(),Head.class);
                startActivity(a);
                break;

            case R.id.other:
                Intent b=new Intent(getBaseContext(),OnlineActivity.class);
                startActivity(b);
                break;

        }
}}
