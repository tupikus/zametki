package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Create extends AppCompatActivity {

    TextView tvText_Zam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        tvText_Zam = (TextView) findViewById(R.id.tvText_Zam);
        Intent intent = getIntent();

        String text_zametok = intent.getStringExtra("text");
        tvText_Zam.setText(text_zametok);
    }
}
