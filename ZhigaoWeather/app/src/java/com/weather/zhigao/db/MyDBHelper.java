package com.weather.zhigao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.weather.zhigao.model.CityTable;

public class MyDBHelper extends SQLiteOpenHelper {

    public MyDBHelper(Context context) {
        //创建数据库
        super(context, "city.db", null, 1);
    }

    /**
     * 第一次创建数据库的时候回调该方法，创建表以及表数据的初始化
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CityTable.CREATE_TABLE);

    }

    /**
     * 数据库升级的时候回调该方法
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //给表添加一个字段
        //db.execSQL("alter table person add age integer");
    }

    /**
     * 数据库打开的时候回调该方法
     *
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
