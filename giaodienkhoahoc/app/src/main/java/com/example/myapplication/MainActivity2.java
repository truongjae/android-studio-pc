package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Thông tin liên hệ:");
        this.tvInfo = findViewById(R.id.tvInfo);

        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("result");
        this.tvInfo.setText(result);
    }
}