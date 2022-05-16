package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtName, edtDonViTinh, edtDonGia;
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
        btnUpdate = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        Student student = database.findById(id);
        if(student!=null){
            edtName.setText(student.getName());
            edtDonViTinh.setText(student.getDonGia());
            edtDonGia.setText(String.valueOf(student.getDonGia()));

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(id, edtName.getText().toString(),
                        edtDonViTinh.getText().toString(), edtDonGia.getText().toString());
                if (student != null) database.update(student);
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