package com.example.giaiphuongtrinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView txtResult2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtResult2 = findViewById(R.id.txtResult2);
        Bundle bundle = getIntent().getExtras();
        String result = bundle.getString("result");
        txtResult2.setText(result);

    }
}