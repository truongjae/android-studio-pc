package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edtMH;
    private Button btnThem, btnXoa, btnSua;
    private ListView listView;

    private ArrayList<String> arrMH;
    private ArrayAdapter<String> arrayAdapter;

    private int indexItem=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMH = findViewById(R.id.edtMH);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        listView = findViewById(R.id.listView);

        arrMH = new ArrayList<>();
        arrayAdapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrMH);
        listView.setAdapter(arrayAdapter);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monHoc = edtMH.getText().toString();
                if(monHoc.isEmpty()) return;
                arrMH.add(monHoc);
                edtMH.setText("");
                arrayAdapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtMH.setText(arrMH.get(i));
                indexItem = i;

            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrMH.set(indexItem,edtMH.getText().toString());
                edtMH.setText("");
                arrayAdapter.notifyDataSetChanged();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrMH.remove(indexItem);
                edtMH.setText("");
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}