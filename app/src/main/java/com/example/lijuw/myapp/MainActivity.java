package com.example.lijuw.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Templates;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView button=findViewById(R.id.btn_to_daletou);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
 startActivity(new Intent(MainActivity.this,BettingActivity.class));
            }
        });
    }
}
