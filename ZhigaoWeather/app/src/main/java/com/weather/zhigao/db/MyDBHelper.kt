package com.weather.zhigao.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import com.weather.zhigao.model.CityTable

class MyDBHelper(context: Context)//创建数据库
    : SQLiteOpenHelper(context, "city.db", null, 1) {

    /**
     * 第一次创建数据库的时候回调该方法，创建表以及表数据的初始化
     *
     * @param db
     */
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CityTable.CREATE_TABLE)

    }

    /**
     * 数据库升级的时候回调该方法
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //给表添加一个字段
        //db.execSQL("alter table person add age integer");
    }

    /**
     * 数据库打开的时候回调该方法
     *
     * @param db
     */
    override fun onOpen(db: SQLiteDatabase) {
        super.onOpen(db)
    }
}
