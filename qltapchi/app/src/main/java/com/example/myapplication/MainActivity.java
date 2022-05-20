package com.example.myapplication;

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

    private Database database;
    private List<TapChi> tapChiList;
    private TapChiAdapter tapChiAdapter;
    private int saveIdStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch= findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch);

        listView = findViewById(R.id.lvStudent);

        database = new Database(this);

        tapChiList = database.getAll();

        setAdapter();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtSearch.getText().toString().equals("")){
                    tapChiList.clear();
                    tapChiList.addAll(database.getAll());
                    setAdapter();
                }
                else{
                    TapChi tapChi = database.findById(Integer.parseInt(edtSearch.getText().toString()));
                    if(tapChi !=null){
                        tapChiList.clear();
                        tapChiList.addAll(Arrays.asList(tapChi));
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
                TapChi tapChi = tapChiList.get(i);
                saveIdStudent = tapChi.getId();
            }
        });


        // gan menu context cho view
        registerForContextMenu(listView);

    }

    private void setAdapter(){
        if(tapChiAdapter ==null){
            tapChiAdapter = new TapChiAdapter(this,R.layout.activity_item_list_tap_chi, tapChiList);
            listView.setAdapter(tapChiAdapter);
        }
        else{
            tapChiAdapter.notifyDataSetChanged();
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
                database.delete(saveIdStudent);
                tapChiList.clear();
                tapChiList.addAll(database.getAll());
                setAdapter();
                Toast.makeText(MainActivity.this,"Xoá thành công",Toast.LENGTH_LONG).show();
                break;
            case R.id.menuContextUpdate:
                Intent intent= new Intent(MainActivity.this, UpdateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id",saveIdStudent);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }
}