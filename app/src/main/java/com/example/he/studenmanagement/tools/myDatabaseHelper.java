package com.example.he.studenmanagement.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 单例模式
 * Created by he on 2016/10/3.
 */
public class myDatabaseHelper extends SQLiteOpenHelper {
    private static myDatabaseHelper instance;
    public static final String CREATE_ADMIN = "create table admin(id integer primary key autoincrement, name text,password text)";//创建管理员表

    public static final String CREATE_STUDENT = "create table student(id text primary key,name text,password text,sex text,number text,mathScore integer)";//创建学生表

    public static final String CREATE_BANK = "create table bank(id integer primary key,type integer,difficult integer,title text,idA  text,idB  text,idC  text,idD  text,trueOption text)";//创建学生表

    public static final String CREATE_PAPER = "create table paper(id text primary key ,name text)";
    private myDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ADMIN);
        db.execSQL(CREATE_BANK);
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_PAPER);
        db.execSQL("alter table student add  column ranking integer");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==1){
            db.execSQL("alter table student add  column ranking integer");
        }
    }

    public static myDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new myDatabaseHelper(context, "StudentManagement.db", null, 2);
        }
        return instance;

    }
}
