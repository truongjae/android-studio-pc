package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    private EditText edtName, edtLoai, edtDonViTinh, edtDonGia,edtNoiSX;
    private Button btnInsert, btnExit;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        database = new Database(this);
        edtName = findViewById(R.id.edtName);
        edtLoai = findViewById(R.id.edtLoai);
        edtDonViTinh = findViewById(R.id.edtDonViTinh);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtNoiSX = findViewById(R.id.edtNoiSX);
        btnInsert = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaQuaSach hoaQuaSach = new HoaQuaSach(edtName.getText().toString(),edtLoai.getText().toString(),
                        edtDonViTinh.getText().toString(),
                        Integer.parseInt(edtDonGia.getText().toString()),edtNoiSX.getText().toString());
                if(hoaQuaSach !=null) database.insert(hoaQuaSach);
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