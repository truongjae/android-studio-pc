package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtName, edtDonViTinh, edtDonGia, edtHangSanXuat;
    private Button btnUpdate, btnExit;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        edtName = findViewById(R.id.edtName);
        edtDonViTinh = findViewById(R.id.edtDonViTinh);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtHangSanXuat = findViewById(R.id.edtHangSanXuat);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnExit = findViewById(R.id.btnExit);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        VatTu vatTu = database.findById(id);
        if(vatTu !=null){
            edtName.setText(vatTu.getName());
            edtDonViTinh.setText(vatTu.getDonViTinh());
            edtDonGia.setText(String.valueOf(vatTu.getDonGia()));
            edtHangSanXuat.setText(vatTu.getHangSanXuat());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VatTu vatTu = new VatTu(id,Integer.parseInt(edtDonGia.getText().toString()),
                        edtName.getText().toString(), edtHangSanXuat.getText().toString(),edtDonViTinh.getText().toString());
                if (vatTu != null) database.update(vatTu);
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