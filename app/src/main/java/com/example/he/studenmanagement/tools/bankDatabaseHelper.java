package com.example.he.studenmanagement.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 单例模式
 * Created by he on 2016/10/3.
 */
public class bankDatabaseHelper extends SQLiteOpenHelper {
    private static bankDatabaseHelper instance;
    public static final String CREATE_BANK = "create table bank(id integer primary key autoincrement, " +
            "title text,idA text,idB text," +
            "idC text,idD text,trueOption text)";//创建管理员表


    private bankDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_BANK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion==1){
            db.execSQL("alter table bank ");
        }
    }

    public static bankDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new bankDatabaseHelper(context, "StudentManagement.db", null, 2);
        }
        return instance;

    }
}
