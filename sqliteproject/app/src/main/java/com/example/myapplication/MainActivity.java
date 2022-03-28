package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtId,edtFullName,edtClass,edtAddress, edtPhone;
    private Button btnInsert,btnUpdate,btnDelete,btnExit;
    private ListView listView;

    private Database database;
    private List<Student> studentList;
    private StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtId = findViewById(R.id.edtId);
        edtFullName = findViewById(R.id.edtFullName);
        edtClass = findViewById(R.id.edtClass);
        edtAddress = findViewById(R.id.edtAddress);
        edtPhone = findViewById(R.id.edtPhone);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnExit = findViewById(R.id.btnExit);

        listView = findViewById(R.id.lvStudent);

        database = new Database(this);

        studentList = database.getAll();

        setAdapter();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student(edtFullName.getText().toString(),edtClass.getText().toString(),
                        edtAddress.getText().toString(),edtPhone.getText().toString());
                if(student!=null) database.insert(student);
                studentList.clear();
                studentList.addAll(database.getAll());
                setAdapter();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = studentList.get(i);
                edtId.setText(String.valueOf(student.getId()));
                edtFullName.setText(student.getFullName());
                edtClass.setText(student.getClazz());
                edtPhone.setText(student.getPhone());
                edtAddress.setText(student.getAddress());
//                btnInsert.setEnabled(false);
//                btnUpdate.setEnabled(true);
            }
        });

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Toast.makeText(MainActivity.this,"Xóa thành công!",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                return true;
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setId(Integer.parseInt(String.valueOf(edtId.getText().toString())));
                student.setFullName(edtFullName.getText().toString());
                student.setClazz(edtClass.getText().toString());
                student.setAddress(edtAddress.getText().toString());
                student.setPhone(edtPhone.getText().toString());
                int result = database.update(student);
                if(result>0){
                    studentList.clear();
                    studentList.addAll(database.getAll());
                    if(studentAdapter!=null) studentAdapter.notifyDataSetChanged();
                }
//                btnInsert.setEnabled(true);
//                btnUpdate.setEnabled(false);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = database.delete(Integer.parseInt(edtId.getText().toString()));
                if(result>0){
                    studentList.clear();
                    studentList.addAll(database.getAll());
                    if(studentAdapter!=null) studentAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"Xóa thành công!",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(MainActivity.this,"Ấn cc bố chặt tay giờ!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setAdapter(){
        if(studentAdapter==null){
            studentAdapter = new StudentAdapter(this,R.layout.activity_item_list_sv,studentList);
            listView.setAdapter(studentAdapter);
        }
        else{
            studentAdapter.notifyDataSetChanged();
        }
    }

}