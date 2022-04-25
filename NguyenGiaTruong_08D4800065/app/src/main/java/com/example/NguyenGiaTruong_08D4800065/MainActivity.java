package com.example.NguyenGiaTruong_08D4800065;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText edtBienKiemSoat, edtTenChuXe, edtHangXe, edtTrongTai, edtHinhThucKinhDoanh;
    private Button btnInsert,btnUpdate,btnDelete,btnExit;
    private ListView listView;

    private Database database;
    private List<NguyenGiaTruong_Xe> nguyenGiaTruongXeList;
    private NguyenGiaTruongXeAdapter nguyenGiaTruongXeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtBienKiemSoat = findViewById(R.id.edtBienKiemSoat);
        edtTenChuXe = findViewById(R.id.edtTenChuXe);
        edtHangXe = findViewById(R.id.edtHangXe);
        edtTrongTai = findViewById(R.id.edtTrongTai);
        edtHinhThucKinhDoanh = findViewById(R.id.edtHinhThucKinhDoanh);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnExit = findViewById(R.id.btnExit);

        listView = findViewById(R.id.lvXe);

        database = new Database(this);

        nguyenGiaTruongXeList = database.getAll();

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
                NguyenGiaTruong_Xe nguyenGiaTruongXe = new NguyenGiaTruong_Xe(edtBienKiemSoat.getText().toString(),edtTenChuXe.getText().toString(), edtHangXe.getText().toString(),
                        Integer.parseInt(edtTrongTai.getText().toString()), edtHinhThucKinhDoanh.getText().toString());
                if(nguyenGiaTruongXe !=null) database.insert(nguyenGiaTruongXe);
                nguyenGiaTruongXeList.clear();
                nguyenGiaTruongXeList.addAll(database.getAll());
                setAdapter();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NguyenGiaTruong_Xe nguyenGiaTruongXe = nguyenGiaTruongXeList.get(i);
                edtBienKiemSoat.setText(String.valueOf(nguyenGiaTruongXe.getBienKiemSoat()));
                edtTenChuXe.setText(nguyenGiaTruongXe.getTenChuXe());
                edtHangXe.setText(nguyenGiaTruongXe.getHangXe());
                edtHinhThucKinhDoanh.setText(nguyenGiaTruongXe.getHinhThucKinhDoanh());
                edtTrongTai.setText(String.valueOf(nguyenGiaTruongXe.getTrongTai()));

            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NguyenGiaTruong_Xe nguyenGiaTruongXe = new NguyenGiaTruong_Xe();
                nguyenGiaTruongXe.setBienKiemSoat(edtBienKiemSoat.getText().toString());
                nguyenGiaTruongXe.setTenChuXe(edtTenChuXe.getText().toString());
                nguyenGiaTruongXe.setHangXe(edtHangXe.getText().toString());
                nguyenGiaTruongXe.setTrongTai(Integer.parseInt(edtTrongTai.getText().toString()));
                nguyenGiaTruongXe.setHinhThucKinhDoanh(edtHinhThucKinhDoanh.getText().toString());
                int result = database.update(nguyenGiaTruongXe);
                if(result>0){
                    nguyenGiaTruongXeList.clear();
                    nguyenGiaTruongXeList.addAll(database.getAll());
                    if(nguyenGiaTruongXeAdapter !=null) nguyenGiaTruongXeAdapter.notifyDataSetChanged();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = database.delete(edtBienKiemSoat.getText().toString());
                if(result>0){
                    nguyenGiaTruongXeList.clear();
                    nguyenGiaTruongXeList.addAll(database.getAll());
                    if(nguyenGiaTruongXeAdapter !=null) nguyenGiaTruongXeAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"Xóa thành công!",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(MainActivity.this,"Biển kiểm soát không tồn tại",Toast.LENGTH_LONG).show();
            }
        });

        // gan menu context cho view
        registerForContextMenu(listView);

    }

    private void setAdapter(){
        if(nguyenGiaTruongXeAdapter ==null){
            nguyenGiaTruongXeAdapter = new NguyenGiaTruongXeAdapter(this,R.layout.activity_item_list_sv, nguyenGiaTruongXeList);
            listView.setAdapter(nguyenGiaTruongXeAdapter);
        }
        else{
            nguyenGiaTruongXeAdapter.notifyDataSetChanged();
        }
    }

    //menu option

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }
    // xu ly su kien cho menu option


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuAdd:
                NguyenGiaTruong_Xe nguyenGiaTruongXe = new NguyenGiaTruong_Xe(edtTenChuXe.getText().toString(), edtHangXe.getText().toString(),
                        Integer.parseInt(edtTrongTai.getText().toString()), edtHinhThucKinhDoanh.getText().toString());
                if(nguyenGiaTruongXe !=null) database.insert(nguyenGiaTruongXe);
                nguyenGiaTruongXeList.clear();
                nguyenGiaTruongXeList.addAll(database.getAll());
                setAdapter();
                break;
            case R.id.menuUpdate:

                break;
            case R.id.menuClear:
                break;
            case R.id.menuExit:
                break;
            default:
                break;
        }
        return true;
    }

    //menu context


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v==listView){
            getMenuInflater().inflate(R.menu.menu_context,menu);
        }
        if(v==btnUpdate){
            getMenuInflater().inflate(R.menu.menu_context,menu);
        }
    }
    // xu ly su kien trong context menu

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuContextDelete:
                int result = database.delete(edtBienKiemSoat.getText().toString());
                if(result>0){
                    nguyenGiaTruongXeList.clear();
                    nguyenGiaTruongXeList.addAll(database.getAll());
                    if(nguyenGiaTruongXeAdapter !=null) nguyenGiaTruongXeAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"Xóa thành công!",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(MainActivity.this,"Biển kiểm soát",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuContextUpdate:
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}