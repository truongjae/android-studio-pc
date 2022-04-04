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

    private EditText edtFullName, edtPhone, edtEmail;
    private Button btnUpdate, btnExit;
    private RadioGroup radioGroup;
    private RadioButton choice1, choice2;
    private String gender = "Nam";
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        database = new Database(this);
        edtFullName = findViewById(R.id.edtFullName);
        edtPhone = findViewById(R.id.edtPhone);
        edtEmail = findViewById(R.id.edtEmail);
        btnUpdate = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);
        radioGroup = findViewById(R.id.radioGroup);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        Student student = database.findById(id);
        if(student!=null){
            edtFullName.setText(student.getFullName());
            edtPhone.setText(student.getPhone());
            edtEmail.setText(student.getEmail());
            if(student.getGender().equals("Nam"))
                choice1.setChecked(true);
            else
                choice2.setChecked(true);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                choiceGender(radioGroup, i);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(id,edtFullName.getText().toString(), gender,
                        edtPhone.getText().toString(), edtEmail.getText().toString());
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


    public void choiceGender(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();
        if (checkedRadioId == R.id.choice1)
            gender = "Nam";
        if (checkedRadioId == R.id.choice2)
            gender = "Nu";
    }
}