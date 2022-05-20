package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtName, edtLoai, edtSoXB, edtNhaXB, edtDonGia;
    private Button btnUpdate, btnExit;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        edtName = findViewById(R.id.edtName);
        edtLoai = findViewById(R.id.edtLoai);
        edtSoXB = findViewById(R.id.edtSoXB);
        edtNhaXB = findViewById(R.id.edtNhaXB);
        edtDonGia = findViewById(R.id.edtDonGia);
        btnUpdate = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        TapChi tapChi = database.findById(id);
        if(tapChi !=null){
            edtName.setText(tapChi.getName());
            edtLoai.setText(tapChi.getLoai());
            edtSoXB.setText(String.valueOf(tapChi.getSoXB()));
            edtNhaXB.setText(tapChi.getNhaXB());
            edtDonGia.setText(String.valueOf(tapChi.getDonGia()));
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TapChi tapChi = new TapChi(id,edtName.getText().toString(),edtLoai.getText().toString()
                        ,Integer.parseInt(edtSoXB.getText().toString()),edtNhaXB.getText().toString()
                        ,Integer.parseInt(edtDonGia.getText().toString()));
               // (String name, String loai, int soXB, String nhaXB, int donGia)
                if (tapChi != null) database.update(tapChi);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
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