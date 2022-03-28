package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String  DATABASE_NAME =  "QLSV.db";
    private static final String TABLE_NAME = "sinhvien";
    private static final String ID = "id";
    private static final String FULLNAME = "fullname";
    private static final String CLASS = "class";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";

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
                +FULLNAME+" Text,"
                +CLASS+" Text,"
                +ADDRESS+" Text,"
                +PHONE+" Text)";
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
        values.put(FULLNAME,student.getFullName());
        values.put(CLASS,student.getClazz());
        values.put(ADDRESS,student.getAddress());
        values.put(PHONE,student.getPhone());
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();

    }
    public int update (Student student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FULLNAME,student.getFullName());
        values.put(CLASS,student.getClazz());
        values.put(ADDRESS,student.getAddress());
        values.put(PHONE,student.getPhone());
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
                student.setFullName(cursor.getString(1));
                student.setClazz(cursor.getString(2));
                student.setAddress(cursor.getString(3));
                student.setPhone(cursor.getString(4));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        return studentList;
    }
    public Student findById(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,new String[]{ID,FULLNAME,CLASS,ADDRESS,PHONE}, ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor!=null){
            cursor.moveToFirst();
            Student student = new Student();
            student.setFullName(cursor.getString(1));
            student.setClazz(cursor.getString(2));
            student.setAddress(cursor.getString(3));
            student.setPhone(cursor.getString(4));
            cursor.close();
            sqLiteDatabase.close();
            return student;
        }
        return null;
    }
}
