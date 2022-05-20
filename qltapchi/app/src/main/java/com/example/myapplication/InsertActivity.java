package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    private EditText edtName, edtLoai, edtSoXB, edtNhaXB, edtDonGia;
    private Button btnInsert, btnExit;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        database = new Database(this);
        edtName = findViewById(R.id.edtName);
        edtLoai = findViewById(R.id.edtLoai);
        edtSoXB = findViewById(R.id.edtSoXB);
        edtNhaXB = findViewById(R.id.edtNhaXB);
        edtDonGia = findViewById(R.id.edtDonGia);
        btnInsert = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TapChi tapChi = new TapChi(edtName.getText().toString(),edtLoai.getText().toString()
                        ,Integer.parseInt(edtSoXB.getText().toString()),edtNhaXB.getText().toString()
                        ,Integer.parseInt(edtDonGia.getText().toString()));
//                (String name, String loai, int soXB, String nhaXB, int donGia)
                if(tapChi !=null) database.insert(tapChi);
                Intent intent = new Intent(InsertActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}