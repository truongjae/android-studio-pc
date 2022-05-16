package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class InsertActivity extends AppCompatActivity {

    private EditText edtName, edtDonViTinh, edtDonGia;
    private Button btnInsert, btnExit;
    private Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        database = new Database(this);
        edtName = findViewById(R.id.edtName);
        edtDonViTinh = findViewById(R.id.edtDonViTinh);
        edtDonGia = findViewById(R.id.edtDonGia);
        btnInsert = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(edtName.getText().toString(),
                        edtDonViTinh.getText().toString(), edtDonGia.getText().toString());
                if(student!=null) database.insert(student);
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