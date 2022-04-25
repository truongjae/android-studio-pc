package com.example.NguyenGiaTruong_08D4800065;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QL_vantai.db";
    private static final String TABLE_NAME = "t_xe";
    private static final String BIENKIEMSOAT = "bienkiemsoat";
    private static final String TENCHUXE = "tenchuxe";
    private static final String HANGXE = "hangxe";
    private static final String TRONGTAI = "trongtai";
    private static final String HINHTHUCKINHDOANH = "hinhthuckinhdoanh";

    private static int VERSION = 1;
    private Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME, null , VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table "+TABLE_NAME+"("
                + BIENKIEMSOAT +" Text primary key,"
                + TENCHUXE +" Text,"
                + HANGXE +" Text,"
                + TRONGTAI +" int,"
                + HINHTHUCKINHDOANH +" Text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void insert(NguyenGiaTruong_Xe nguyenGiaTruongXe){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BIENKIEMSOAT,nguyenGiaTruongXe.getBienKiemSoat());
        values.put(TENCHUXE, nguyenGiaTruongXe.getTenChuXe());
        values.put(HANGXE, nguyenGiaTruongXe.getHangXe());
        values.put(TRONGTAI, nguyenGiaTruongXe.getTrongTai());
        values.put(HINHTHUCKINHDOANH, nguyenGiaTruongXe.getHinhThucKinhDoanh());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (NguyenGiaTruong_Xe nguyenGiaTruongXe){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TENCHUXE, nguyenGiaTruongXe.getTenChuXe());
        values.put(HANGXE, nguyenGiaTruongXe.getHangXe());
        values.put(TRONGTAI, nguyenGiaTruongXe.getTrongTai());
        values.put(HINHTHUCKINHDOANH, nguyenGiaTruongXe.getHinhThucKinhDoanh());
        int result = sqLiteDatabase.update(TABLE_NAME,values, BIENKIEMSOAT +"=?",new String[]{String.valueOf(nguyenGiaTruongXe.getBienKiemSoat())});
        sqLiteDatabase.close();
        return result;
    }
    public int delete (String bienKiemSoat){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME, BIENKIEMSOAT +"=?",new String[]{bienKiemSoat});
        sqLiteDatabase.close();
        return result;
    }
    public List<NguyenGiaTruong_Xe> getAll(){
        List<NguyenGiaTruong_Xe> nguyenGiaTruongXeList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "select * from "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                NguyenGiaTruong_Xe nguyenGiaTruongXe = new NguyenGiaTruong_Xe();
                nguyenGiaTruongXe.setBienKiemSoat(cursor.getString(0));
                nguyenGiaTruongXe.setTenChuXe(cursor.getString(1));
                nguyenGiaTruongXe.setHangXe(cursor.getString(2));
                nguyenGiaTruongXe.setTrongTai(cursor.getInt(3));
                nguyenGiaTruongXe.setHinhThucKinhDoanh(cursor.getString(4));
                nguyenGiaTruongXeList.add(nguyenGiaTruongXe);
            } while (cursor.moveToNext());
        }

        return nguyenGiaTruongXeList;
    }
    public NguyenGiaTruong_Xe findById(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{BIENKIEMSOAT, TENCHUXE, HANGXE, TRONGTAI, HINHTHUCKINHDOANH}, BIENKIEMSOAT +"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
            NguyenGiaTruong_Xe nguyenGiaTruongXe = new NguyenGiaTruong_Xe();
            nguyenGiaTruongXe.setTenChuXe(cursor.getString(1));
            nguyenGiaTruongXe.setHangXe(cursor.getString(2));
            nguyenGiaTruongXe.setTrongTai(cursor.getInt(3));
            nguyenGiaTruongXe.setHinhThucKinhDoanh(cursor.getString(4));
            cursor.close();
            sqLiteDatabase.close();
            return nguyenGiaTruongXe;
        }
        return null;
    }
}
