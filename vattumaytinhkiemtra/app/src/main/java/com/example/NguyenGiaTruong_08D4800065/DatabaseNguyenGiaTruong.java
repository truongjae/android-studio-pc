package com.example.NguyenGiaTruong_08D4800065;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseNguyenGiaTruong extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QLVATTU_MAYTINH.db";
    private static final String TABLE_NAME = "t_maytinh";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String LOAIMAYTINH = "loaimaytinh";
    private static final String NAMSX = "namsx";
    private static final String HANGSX = "hangsx";
    private static final String DONGIA = "dongia";
    private static final String SOLUONG = "soluong";

    private static int VERSION = 1;
    private Context context;

    public DatabaseNguyenGiaTruong(Context context) {
        super(context, DATABASE_NAME, null , VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table "+TABLE_NAME+"("
                +ID+" Text,"
                +NAME +" Text,"
                +LOAIMAYTINH+" Text,"
                +NAMSX+" integer,"
                +HANGSX+" Text,"
                +DONGIA+" integer,"
                +SOLUONG+" integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void insert(MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, mayTinhNguyenGiaTruong.getId());
        values.put(NAME, mayTinhNguyenGiaTruong.getName());
        values.put(LOAIMAYTINH, mayTinhNguyenGiaTruong.getLoaiMayTinh());
        values.put(NAMSX, mayTinhNguyenGiaTruong.getNamSX());
        values.put(HANGSX, mayTinhNguyenGiaTruong.getHangSX());
        values.put(DONGIA, mayTinhNguyenGiaTruong.getDonGia());
        values.put(SOLUONG, mayTinhNguyenGiaTruong.getSoLuong());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, mayTinhNguyenGiaTruong.getName());
        values.put(LOAIMAYTINH, mayTinhNguyenGiaTruong.getLoaiMayTinh());
        values.put(NAMSX, mayTinhNguyenGiaTruong.getNamSX());
        values.put(HANGSX, mayTinhNguyenGiaTruong.getHangSX());
        values.put(DONGIA, mayTinhNguyenGiaTruong.getDonGia());
        values.put(SOLUONG, mayTinhNguyenGiaTruong.getSoLuong());
        int result = sqLiteDatabase.update(TABLE_NAME,values,ID +"=?",new String[]{String.valueOf(mayTinhNguyenGiaTruong.getId())});
        sqLiteDatabase.close();
        return result;
    }
    public int delete (String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{id});
        sqLiteDatabase.close();
        return result;
    }
    public List<MayTinhNguyenGiaTruong> getAll(){
        List<MayTinhNguyenGiaTruong> mayTinhNguyenGiaTruongList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "select * from "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = new MayTinhNguyenGiaTruong();
                mayTinhNguyenGiaTruong.setId(cursor.getString(0));
                mayTinhNguyenGiaTruong.setName(cursor.getString(1));
                mayTinhNguyenGiaTruong.setLoaiMayTinh(cursor.getString(2));
                mayTinhNguyenGiaTruong.setNamSX(Integer.parseInt(cursor.getString(3)));
                mayTinhNguyenGiaTruong.setHangSX(cursor.getString(4));
                mayTinhNguyenGiaTruong.setDonGia(Integer.parseInt(cursor.getString(5)));
                mayTinhNguyenGiaTruong.setSoLuong(Integer.parseInt(cursor.getString(6)));
                mayTinhNguyenGiaTruongList.add(mayTinhNguyenGiaTruong);
            } while (cursor.moveToNext());
        }

        return mayTinhNguyenGiaTruongList;
    }
    public MayTinhNguyenGiaTruong findById(String id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID, NAME,LOAIMAYTINH,NAMSX,HANGSX,DONGIA,SOLUONG}, ID+"=?",new String[]{id},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                MayTinhNguyenGiaTruong mayTinhNguyenGiaTruong = new MayTinhNguyenGiaTruong();
                mayTinhNguyenGiaTruong.setId(cursor.getString(0));
                mayTinhNguyenGiaTruong.setName(cursor.getString(1));
                mayTinhNguyenGiaTruong.setLoaiMayTinh(cursor.getString(2));
                mayTinhNguyenGiaTruong.setNamSX(Integer.parseInt(cursor.getString(3)));
                mayTinhNguyenGiaTruong.setHangSX(cursor.getString(4));
                mayTinhNguyenGiaTruong.setDonGia(Integer.parseInt(cursor.getString(5)));
                mayTinhNguyenGiaTruong.setSoLuong(Integer.parseInt(cursor.getString(6)));
                cursor.close();
                sqLiteDatabase.close();
                return mayTinhNguyenGiaTruong;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
