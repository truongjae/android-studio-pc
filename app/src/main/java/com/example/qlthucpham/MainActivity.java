package com.example.qlthucpham;

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

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText edtSearch;
    private Button btnSearch;
    private ListView listView;

    private DataBase dataBase;
    private List<ThucPham> thucPhamList;
    private ThucPhamAdapter thucPhamAdapter;
    private int saveIdThucPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        listView = findViewById(R.id.lvThucPham);

        dataBase =new DataBase(this);
        thucPhamList = dataBase.getAll();

        setAdapter();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtSearch.getText().toString().equals("")){
                    thucPhamList.clear();
                    thucPhamList.addAll(dataBase.getAll());
                    setAdapter();
                }
                else{
                    ThucPham thucPham = dataBase.findById(Integer.parseInt(edtSearch.getText().toString()));
                    if(thucPham!=null){
                        thucPhamList.clear();
                        thucPhamList.addAll(Arrays.asList(thucPham));
                        setAdapter();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Không tìm thấy", Toast.LENGTH_LONG).show();
                }

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ThucPham thucPham = thucPhamList.get(i);
                saveIdThucPham = thucPham.getId();
            }
        });


        // gan menu context cho view
        registerForContextMenu(listView);

    }
    private void setAdapter(){
        if(thucPhamAdapter ==null){
            thucPhamAdapter = new ThucPhamAdapter(this,R.layout.activity_item_list_thuc_pham,thucPhamList);
            listView.setAdapter(thucPhamAdapter);
        }
        else{
            thucPhamAdapter.notifyDataSetChanged();
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
                Intent intent= new Intent(MainActivity.this, InsertActivity.class);
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
                dataBase.delete(saveIdThucPham);
                thucPhamList.clear();
                thucPhamList.addAll(dataBase.getAll());
                setAdapter();
                Toast.makeText(MainActivity.this,"Xoá thành công",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuContextUpdate:
                Intent intent= new Intent(MainActivity.this, UpdateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",saveIdThucPham);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}