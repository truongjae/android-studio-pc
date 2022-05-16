package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QLTHUCPHAM2.db";
    private static final String TABLE_NAME = "thucpham";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DONVITINH = "donvitinh";
    private static final String DONGIA = "dongia";

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
                + NAME +" Text,"
                +DONVITINH+" Text,"
                +DONGIA+" Text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
    public void insert(Student student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,student.getName());
        values.put(DONVITINH,student.getDonViTinh());
        values.put(DONGIA,student.getDonGia());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (Student student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,student.getName());
        values.put(DONVITINH,student.getDonViTinh());
        values.put(DONGIA,student.getDonGia());
        int result = sqLiteDatabase.update(TABLE_NAME,values,ID +"=?",new String[]{String.valueOf(student.getId())});
        sqLiteDatabase.close();
        return result;
    }
    public int delete (int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete(TABLE_NAME,ID+"=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
        return result;
    }
    public List<Student> getAll(){
        List<Student> studentList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String sql = "select * from "+TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do {
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setDonViTinh(cursor.getString(2));
                student.setDonGia(cursor.getString(3));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }
    public Student findById(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID, NAME,DONVITINH,DONGIA}, ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            try {
                cursor.moveToFirst();
                Student student = new Student();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setDonViTinh(cursor.getString(2));
                student.setDonGia(cursor.getString(3));
                cursor.close();
                sqLiteDatabase.close();
                return student;
            }
            catch (Exception e){
                return null;
            }
        }
        return null;
    }
}
