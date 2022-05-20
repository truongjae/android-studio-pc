package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QLTAPCHI.db";
    private static final String TABLE_NAME = "tapchi";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOAI = "loai";
    private static final String SOXB = "soxb";
    private static final String NXB = "nxb";
    private static final String DONGIA = "dongia";

    private static int VERSION = 1;
    private Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME, null , VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table "+TABLE_NAME+"("
                +ID+" integer primary key autoincrement,"
                + NAME +" Text,"
                + LOAI +" Text,"
                + SOXB +" integer,"
                + NXB+" Text,"
                + DONGIA+" integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void insert(TapChi tapChi){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, tapChi.getName());
        values.put(LOAI, tapChi.getLoai());
        values.put(SOXB, tapChi.getSoXB());
        values.put(NXB, tapChi.getNhaXB());
        values.put(DONGIA, tapChi.getDonGia());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (TapChi tapChi){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, tapChi.getName());
        values.put(LOAI, tapChi.getLoai());
        values.put(SOXB, tapChi.getSoXB());
        values.put(NXB, tapChi.getNhaXB());
        values.put(DONGIA, tapChi.getDonGia());
        int result = sqLiteDatabase.update(TABLE_NAME,values,ID +"=?",new String[]{String.valueOf(tapChi.getId())});
        sqLiteDatabase.close();
        return result;
    }
    public int delete (int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }
    public List<TapChi> getAll(){
        List<TapChi> tapChiList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "select * from "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                TapChi tapChi = new TapChi();
                tapChi.setId(cursor.getInt(0));
                tapChi.setName(cursor.getString(1));
                tapChi.setLoai(cursor.getString(2));
                tapChi.setSoXB(cursor.getInt(3));
                tapChi.setNhaXB(cursor.getString(4));
                tapChi.setDonGia(cursor.getInt(5));
                tapChiList.add(tapChi);
            } while (cursor.moveToNext());
        }

        return tapChiList;
    }
    public TapChi findById(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID, NAME,LOAI,SOXB,NXB,DONGIA}, ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                TapChi tapChi = new TapChi();
                tapChi.setId(cursor.getInt(0));
                tapChi.setName(cursor.getString(1));
                tapChi.setLoai(cursor.getString(2));
                tapChi.setSoXB(cursor.getInt(3));
                tapChi.setNhaXB(cursor.getString(4));
                tapChi.setDonGia(cursor.getInt(5));
                cursor.close();
                sqLiteDatabase.close();
                return tapChi;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
