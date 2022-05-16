package com.example.qlthucpham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtId, edtName,edtDonViTinh,edtDonGia;
    private Button btnUpdate, btnExit;
    private DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtDonViTinh = findViewById(R.id.edtDonViTinh);
        edtDonGia = findViewById(R.id.edtDonGia);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnExit = findViewById(R.id.btnExit);
        dataBase = new DataBase(this);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
            ThucPham thucPham = dataBase.findById(id);
        if(thucPham!=null){
            edtName.setText(thucPham.getName());
            edtDonViTinh.setText(thucPham.getDonViTinh());
            edtDonGia.setText(String.valueOf(thucPham.getDonGia()));
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThucPham thucPham = new ThucPham(id,edtName.getText().toString(),
                        edtDonViTinh.getText().toString(), Float.parseFloat(edtDonGia.getText().toString()));
                if (thucPham != null) dataBase.update(thucPham);
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