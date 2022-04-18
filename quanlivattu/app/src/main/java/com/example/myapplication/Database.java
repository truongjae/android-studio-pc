package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QLVATTU3.db";
    private static final String TABLE_NAME = "t_vattu";
    private static final String ID = "mavattu";
    private static final String NAME = "tenvattu";
    private static final String DONVITINH = "donvitinh";
    private static final String DONGIA = "dongia";
    private static final String HANGSANXUAT = "hangsanxuat";
    private static int VERSION = 1;
    private Context context;

    public Database(Context context) {
        super(context, DATABASE_NAME, null , VERSION);
        this.context = context;
//        context.deleteDatabase("QLSV.db");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table "+TABLE_NAME+"("
                +ID+" integer primary key autoincrement,"
                +NAME+" Text,"
                +DONVITINH+" integer,"
                +DONGIA+" Text,"
                +HANGSANXUAT+" Text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void insert(VatTu vatTu){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, vatTu.getName());
        values.put(DONVITINH, vatTu.getDonViTinh());
        values.put(DONGIA, vatTu.getDonGia());
        values.put(HANGSANXUAT, vatTu.getHangSanXuat());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (VatTu vatTu){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, vatTu.getName());
        values.put(DONVITINH, vatTu.getDonViTinh());
        values.put(DONGIA, vatTu.getDonGia());
        values.put(HANGSANXUAT, vatTu.getHangSanXuat());
        int result = sqLiteDatabase.update(TABLE_NAME,values,ID +"=?",new String[]{String.valueOf(vatTu.getId())});
        sqLiteDatabase.close();
        return result;
    }
    public int delete (int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }
    public List<VatTu> getAll(){
        List<VatTu> vatTuList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "select * from "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                VatTu vatTu = new VatTu();
                vatTu.setId(cursor.getInt(0));
                vatTu.setName(cursor.getString(1));
                vatTu.setDonViTinh(cursor.getString(2));
                vatTu.setDonGia(cursor.getInt(3));
                vatTu.setHangSanXuat(cursor.getString(4));
                vatTuList.add(vatTu);
            } while (cursor.moveToNext());
        }

        return vatTuList;
    }
    public VatTu findById(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID,NAME,DONVITINH,DONGIA,HANGSANXUAT}, ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                VatTu vatTu = new VatTu();
                vatTu.setId(cursor.getInt(0));
                vatTu.setName(cursor.getString(1));
                vatTu.setDonViTinh(cursor.getString(2));
                vatTu.setDonGia(cursor.getInt(3));
                vatTu.setHangSanXuat(cursor.getString(4));
                cursor.close();
                sqLiteDatabase.close();
                return vatTu;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
