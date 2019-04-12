package com.example.ouresic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class denoterequire extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.denoterequire);
        b1=findViewById(R.id.donate);
        b2=findViewById(R.id.require);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com/maps/search/nearby+ESIC+hospital/@28.6131803,77.0244247,13z/data=!3m1!4b1"));
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(denoterequire.this, "OPPS NO STOCK FOUND", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
