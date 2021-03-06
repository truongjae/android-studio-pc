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


public class InsertActivityNguyenGiaTruong extends AppCompatActivity {

    private EditText edtId,edtName, edtLoaiMayTinh, edtNamSX, edtHangSX, edtDonGia, edtSoLuong;
    private Button btnInsert, btnExit;
    private DatabaseNguyenGiaTruong databaseNguyenGiaTruong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        databaseNguyenGiaTruong = new DatabaseNguyenGiaTruong(this);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtLoaiMayTinh = findViewById(R.id.edtLoaiMayTinh);
        edtNamSX = findViewById(R.id.edtNamSX);
        edtHangSX = findViewById(R.id.edtHangSX);
        edtDonGia = findViewById(R.id.edtDonGia);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnInsert = findViewById(R.id.btnInsert);
        btnExit = findViewById(R.id.btnExit);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = new MayTinhNguyenGiaTruong(edtId.getText().toString(),edtName.getText().toString(),
                        edtLoaiMayTinh.getText().toString(),
                        edtHangSX.getText().toString(),
                        Integer.parseInt(edtNamSX.getText().toString()),
                        Integer.parseInt(edtDonGia.getText().toString()),
                        Integer.parseInt(edtSoLuong.getText().toString()));
                if(mayTinhNguyenGiaTruong !=null) {
                    if(databaseNguyenGiaTruong.findById(mayTinhNguyenGiaTruong.getId())==null){
                        databaseNguyenGiaTruong.insert(mayTinhNguyenGiaTruong);
                        Intent intent = new Intent(InsertActivityNguyenGiaTruong.this, MainActivityNguyenGiaTruong.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(InsertActivityNguyenGiaTruong.this,"M?? M??y T??nh ???? T???n T???i",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(InsertActivityNguyenGiaTruong.this,"L???i Khi Th??m",Toast.LENGTH_LONG).show();


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
                Toast.makeText(InsertActivityNguyenGiaTruong.this,"B???n ???? click v??o n??t kh??ng ?????ng ??",Toast.LENGTH_SHORT).show();
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