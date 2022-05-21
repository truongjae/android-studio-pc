package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QLHOAQUASACH2.db";
    private static final String TABLE_NAME = "t_hoaqua";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOAI = "loai";
    private static final String DONGIA = "dongia";
    private static final String DONVITINH = "donvitinh";
    private static final String NSX = "noisanxuat";

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
                + DONVITINH +" Text,"
                + DONGIA+" integer,"
                + NSX+" Text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void insert(HoaQuaSach hoaQuaSach){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, hoaQuaSach.getName());
        values.put(LOAI, hoaQuaSach.getLoai());
        values.put(DONVITINH, hoaQuaSach.getDonViTinh());
        values.put(DONGIA, hoaQuaSach.getDonGia());
        values.put(NSX, hoaQuaSach.getNoiSX());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (HoaQuaSach hoaQuaSach){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, hoaQuaSach.getName());
        values.put(LOAI, hoaQuaSach.getLoai());
        values.put(DONVITINH, hoaQuaSach.getDonViTinh());
        values.put(DONGIA, hoaQuaSach.getDonGia());
        values.put(NSX, hoaQuaSach.getNoiSX());
        int result = sqLiteDatabase.update(TABLE_NAME,values,ID +"=?",new String[]{String.valueOf(hoaQuaSach.getId())});
        sqLiteDatabase.close();
        return result;
    }
    public int delete (int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }
    public List<HoaQuaSach> getAll(){
        List<HoaQuaSach> hoaQuaSachList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "select * from "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                HoaQuaSach hoaQuaSach = new HoaQuaSach();
                hoaQuaSach.setId(cursor.getInt(0));
                hoaQuaSach.setName(cursor.getString(1));
                hoaQuaSach.setLoai(cursor.getString(2));
                hoaQuaSach.setDonViTinh(cursor.getString(3));
                hoaQuaSach.setDonGia(cursor.getInt(4));
                hoaQuaSach.setNoiSX(cursor.getString(5));
                hoaQuaSachList.add(hoaQuaSach);
            } while (cursor.moveToNext());
        }

        return hoaQuaSachList;
    }
    public HoaQuaSach findById(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID, NAME,LOAI,DONVITINH,DONGIA,NSX}, ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                HoaQuaSach hoaQuaSach = new HoaQuaSach();
                hoaQuaSach.setId(cursor.getInt(0));
                hoaQuaSach.setName(cursor.getString(1));
                hoaQuaSach.setLoai(cursor.getString(2));
                hoaQuaSach.setDonViTinh(cursor.getString(3));
                hoaQuaSach.setDonGia(cursor.getInt(4));
                hoaQuaSach.setNoiSX(cursor.getString(5));
                cursor.close();
                sqLiteDatabase.close();
                return hoaQuaSach;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
