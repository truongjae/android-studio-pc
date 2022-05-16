package com.example.NguyenGiaTruong_08D4800065;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import com.example.NguyenGiaTruong_08D480065.R;

import java.util.Arrays;
import java.util.List;

public class MainActivityNguyenGiaTruong extends AppCompatActivity {
    private EditText edtSearch;
    private Button btnSearch;
    private ListView listView;

    private DatabaseNguyenGiaTruong databaseNguyenGiaTruong;
    private List<MayTinhNguyenGiaTruong> mayTinhNguyenGiaTruongList;
    private MayTinhAdapterNguyenGiaTruong mayTinhAdapterNguyenGiaTruong;
    private String saveIdMayTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch= findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        listView = findViewById(R.id.lvMayTinh);

        databaseNguyenGiaTruong = new DatabaseNguyenGiaTruong(this);

        mayTinhNguyenGiaTruongList = databaseNguyenGiaTruong.getAll();

        setAdapter();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtSearch.getText().toString().equals("")){
                    mayTinhNguyenGiaTruongList.clear();
                    mayTinhNguyenGiaTruongList.addAll(databaseNguyenGiaTruong.getAll());
                    setAdapter();
                }
                else{
                    MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = databaseNguyenGiaTruong.findById(edtSearch.getText().toString());
                    if(mayTinhNguyenGiaTruong !=null){
                        mayTinhNguyenGiaTruongList.clear();
                        mayTinhNguyenGiaTruongList.addAll(Arrays.asList(mayTinhNguyenGiaTruong));
                        setAdapter();
                    }
                    else
                        Toast.makeText(MainActivityNguyenGiaTruong.this, "Không tìm thấy", Toast.LENGTH_LONG).show();
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = mayTinhNguyenGiaTruongList.get(i);
                saveIdMayTinh = mayTinhNguyenGiaTruong.getId();
            }
        });


        // gan menu context cho view
        registerForContextMenu(listView);

    }

    private void setAdapter(){
        if(mayTinhAdapterNguyenGiaTruong ==null){
            mayTinhAdapterNguyenGiaTruong = new MayTinhAdapterNguyenGiaTruong(this,R.layout.activity_item_list_mt, mayTinhNguyenGiaTruongList);
            listView.setAdapter(mayTinhAdapterNguyenGiaTruong);
        }
        else{
            mayTinhAdapterNguyenGiaTruong.notifyDataSetChanged();
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
            case R.id.menuInsert:
                Intent intent= new Intent(MainActivityNguyenGiaTruong.this, InsertActivityNguyenGiaTruong.class);
                startActivity(intent);
                break;
            case R.id.menuExit:
                finish();
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
    }
    // xu ly su kien trong context menu

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuContextDelete:
                databaseNguyenGiaTruong.delete(saveIdMayTinh);
                mayTinhNguyenGiaTruongList.clear();
                mayTinhNguyenGiaTruongList.addAll(databaseNguyenGiaTruong.getAll());
                setAdapter();
                Toast.makeText(MainActivityNguyenGiaTruong.this,"Xoá thành công",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuContextUpdate:
                Intent intent= new Intent(MainActivityNguyenGiaTruong.this, UpdateActivityNguyenGiaTruong.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", saveIdMayTinh);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}