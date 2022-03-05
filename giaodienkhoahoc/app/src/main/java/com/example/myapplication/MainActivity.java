package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtPhone;
    private EditText edtEmail;
    private EditText edtAddress;
    private RadioGroup radioGroup;
    private RadioButton rdMale;
    private RadioButton rdFamale;
    private CheckBox cbAndroid;
    private CheckBox cbWebsite;
    private CheckBox cbASP;
    private Button btnSub;
    private String gender;
    private List<String> listCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Đăng ký khóa học:");
        this.edtName = findViewById(R.id.edtName);
        this.edtPhone = findViewById(R.id.edtPhone);
        this.edtEmail = findViewById(R.id.edtEmail);
        this.edtAddress = findViewById(R.id.edtAddress);
        this.radioGroup = findViewById(R.id.radioGroup);
        this.rdMale = findViewById(R.id.rdMale);
        this.rdFamale = findViewById(R.id.rdFeMale);
        this.cbAndroid = findViewById(R.id.cbAndroid);
        this.cbWebsite = findViewById(R.id.cbWebsite);
        this.cbASP = findViewById(R.id.cbASP);
        this.btnSub = findViewById(R.id.btnSub);

        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                choice(radioGroup,i);
            }
        });

        this.rdMale.setChecked(true);

        this.btnSub.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(checkValue()){
                    StringBuilder info = new StringBuilder();
                    info.append("Họ tên: "+edtName.getText().toString()+"\n");
                    info.append("Giới tính: "+gender+"\n");
                    info.append("Số điện thoại: "+edtPhone.getText().toString()+"\n");
                    info.append("Email: "+edtEmail.getText().toString()+"\n");
                    info.append("Địa chỉ: "+edtAddress.getText().toString()+"\n");
                    info.append("Danh sách khóa học:"+"\n");
                    AtomicInteger i= new AtomicInteger(1);
                    listCourse.forEach(course -> {
                        info.append((i.getAndIncrement())+". "+course+"\n");
                    });
                    Intent intent= new Intent(MainActivity.this, MainActivity2.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("result", info.toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });

    }
    public boolean checkValue(){
        this.listCourse = new ArrayList<>();
        if(this.cbAndroid.isChecked()) this.listCourse.add("Android");
        else this.listCourse.remove("Android");

        if(this.cbWebsite.isChecked()) this.listCourse.add("Website");
        else this.listCourse.remove("Website");

        if(this.cbASP.isChecked()) this.listCourse.add("ASP.NET");
        else this.listCourse.remove("ASP.NET");

        if(this.edtName.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Họ tên không được để trống",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(this.edtPhone.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Số điện thoại không được để trống",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(this.edtEmail.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Email không được để trống",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(this.edtAddress.getText().toString().isEmpty()){
            Toast.makeText(MainActivity.this,"Địa chỉ không được để trống",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(listCourse.size() == 0){
            Toast.makeText(MainActivity.this,"Cần chọn ít nhất một khóa học",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
    public void choice(RadioGroup group, int checkedId){
        int checkedRadioId = group.getCheckedRadioButtonId();
        if(checkedRadioId == R.id.rdMale) this.gender = "Nam";
        if(checkedRadioId == R.id.rdFeMale) this.gender = "Nữ";
    }
}