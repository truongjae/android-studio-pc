package com.example.NguyenGiaTruong_08D4800065;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.NguyenGiaTruong_08D480065.R;

public class UpdateActivityNguyenGiaTruong extends AppCompatActivity {

    private EditText edtName, edtLoaiMayTinh, edtNamSX, edtHangSX, edtDonGia, edtSoLuong;
    private Button btnUpdate, btnExit;
    private DatabaseNguyenGiaTruong databaseNguyenGiaTruong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        databaseNguyenGiaTruong = new DatabaseNguyenGiaTruong(this);
        edtName = findViewById(R.id.edtName);
        edtLoaiMayTinh = findViewById(R.id.edtLoaiMayTinh);
        edtNamSX = findViewById(R.id.edtNamSX);
        edtHangSX = findViewById(R.id.edtHangSX);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnUpdate = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = databaseNguyenGiaTruong.findById(id);
        if(mayTinhNguyenGiaTruong !=null){
            edtName.setText(mayTinhNguyenGiaTruong.getName());
            edtLoaiMayTinh.setText(mayTinhNguyenGiaTruong.getLoaiMayTinh());
            edtHangSX.setText(mayTinhNguyenGiaTruong.getHangSX());
            edtNamSX.setText(String.valueOf(mayTinhNguyenGiaTruong.getNamSX()));
            edtDonGia.setText(String.valueOf(mayTinhNguyenGiaTruong.getDonGia()));
            edtSoLuong.setText(String.valueOf(mayTinhNguyenGiaTruong.getSoLuong()));

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = new MayTinhNguyenGiaTruong(id,edtName.getText().toString(),
                        edtLoaiMayTinh.getText().toString(),
                        edtHangSX.getText().toString(),
                        Integer.parseInt(edtNamSX.getText().toString()),
                        Integer.parseInt(edtDonGia.getText().toString()),
                        Integer.parseInt(edtSoLuong.getText().toString()));
                if (mayTinhNguyenGiaTruong != null) databaseNguyenGiaTruong.update(mayTinhNguyenGiaTruong);
                Intent intent = new Intent(UpdateActivityNguyenGiaTruong.this, MainActivityNguyenGiaTruong.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                exit(view);
            }

        });
    }
    public void exit(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("X??c nh???n ????? tho??t..!!!");
        // Icon Of Alert Dialog
//        alertDialogBuilder.setIcon(R.drawable.question);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("B???n c?? mu???n tho??t?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("?????ng ??", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //????ng Activity hi???n t???i
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("Kh??ng ?????ng ??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(UpdateActivityNguyenGiaTruong.this,"B???n ???? click v??o n??t kh??ng ?????ng ??",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialogBuilder.setNeutralButton("H???y", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"B???n ???? click v??o n??t h???y",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}