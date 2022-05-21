package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtName, edtLoai, edtDonViTinh, edtDonGia,edtNoiSX;
    private Button btnUpdate, btnExit;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        edtName = findViewById(R.id.edtName);
        edtLoai = findViewById(R.id.edtLoai);
        edtDonViTinh = findViewById(R.id.edtDonViTinh);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtNoiSX = findViewById(R.id.edtNoiSX);
        btnUpdate = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        HoaQuaSach hoaQuaSach = database.findById(id);
        if(hoaQuaSach !=null){
            edtName.setText(hoaQuaSach.getName());
            edtLoai.setText(hoaQuaSach.getLoai());
            edtDonViTinh.setText(String.valueOf(hoaQuaSach.getDonViTinh()));
            edtDonGia.setText(String.valueOf(hoaQuaSach.getDonGia()));
            edtNoiSX.setText(hoaQuaSach.getNoiSX());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoaQuaSach hoaQuaSach = new HoaQuaSach(id,edtName.getText().toString(),edtLoai.getText().toString(),
                        edtDonViTinh.getText().toString(),
                        Integer.parseInt(edtDonGia.getText().toString()),edtNoiSX.getText().toString());
                if (hoaQuaSach != null) database.update(hoaQuaSach);
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