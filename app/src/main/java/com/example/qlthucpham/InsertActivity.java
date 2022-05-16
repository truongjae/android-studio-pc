package com.example.qlthucpham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {

    private EditText edtName,edtDonViTinh,edtDonGia;
    private Button btnInsert, btnExit;
    private DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        edtName = findViewById(R.id.edtName);
        edtDonViTinh = findViewById(R.id.edtDonViTinh);
        edtDonGia = findViewById(R.id.edtDonGia);
        btnInsert = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);
        dataBase = new DataBase(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThucPham thucPham = new ThucPham(edtName.getText().toString(),
                        edtDonViTinh.getText().toString(),Float.parseFloat(edtDonGia.getText().toString()));
                if(thucPham!=null) dataBase.insert(thucPham);
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