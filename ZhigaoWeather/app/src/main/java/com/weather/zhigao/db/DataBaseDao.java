package com.weather.zhigao.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.weather.zhigao.model.CityAddBean;
import com.weather.zhigao.model.CityTable;
import com.weather.zhigao.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用SQL语句增删改查
 */

public class DataBaseDao {
    String TAG = "DataBaseDao";
    private final MyDBHelper mDbHelper;

    public DataBaseDao(Context context) {
        mDbHelper = new MyDBHelper(context);
    }

    /**
     * 插入
     *
     * @param dataBean
     */
    public void insert(CityAddBean dataBean) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // 执行添加语句
        ContentValues values = new ContentValues();
        values.put(CityTable.COL_LOC, dataBean.getLocation());
        values.put(CityTable.COL_COND, dataBean.getCond_txt());
        values.put(CityTable.COL_TMP_MIN, dataBean.getTmp_min());
        values.put(CityTable.COL_TMP_MAX, dataBean.getTmp_max());

        db.insert(CityTable.TAB_NAME, null, values);
    }

    /**
     * 更新
     *
     * @param location
     * @param dataBean
     */
    public void update(String location, CityAddBean dataBean) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CityTable.COL_LOC, dataBean.getLocation());
        values.put(CityTable.COL_COND, dataBean.getCond_txt());
        values.put(CityTable.COL_TMP_MIN, dataBean.getTmp_min());
        values.put(CityTable.COL_TMP_MAX, dataBean.getTmp_max());

        db.update(CityTable.TAB_NAME, values, CityTable.COL_LOC + "=?", new String[]{location});
    }

    /**
     * 根据id删除
     */
    public void delete(String location) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(CityTable.TAB_NAME, CityTable.COL_LOC + "=?", new String[]{location});
    }

    /**
     * 删除所有
     */
    public void deleteAll() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(CityTable.TAB_NAME, null, null);
    }


    /**
     * 查询所有
     */
    public List<CityAddBean> queryAll() {

        List<CityAddBean> list = new ArrayList<>();
        try {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            String sql = "select * from " + CityTable.TAB_NAME;
            Cursor cursor = db.rawQuery(sql, null);
            while (cursor.moveToNext()) {

                String location = cursor.getString(cursor.getColumnIndex(CityTable.COL_LOC));
                String cond_txt = cursor.getString(cursor.getColumnIndex(CityTable.COL_COND));
                String tmp_min = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MIN));
                String tmp_max = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MAX));

                CityAddBean dataBean = new CityAddBean(location, cond_txt, tmp_min, tmp_max);
                list.add(dataBean);
            }

            cursor.close();
        } catch (Exception e) {
            LogUtil.d(TAG, "e.getMessage()：" + e.getMessage());
        }
        return list;
    }

    /**
     * 根据id查询
     */
    public List<CityAddBean> query(String loc) {
        List<CityAddBean> list = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String sql = "select * from " + CityTable.TAB_NAME + " where " + CityTable.COL_LOC + " =?";
        Cursor cursor = db.rawQuery(sql, new String[]{loc});

        while (cursor.moveToNext()) {
            String location = cursor.getString(cursor.getColumnIndex(CityTable.COL_LOC));
            String cond_txt = cursor.getString(cursor.getColumnIndex(CityTable.COL_COND));
            String tmp_min = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MIN));
            String tmp_max = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MAX));

            CityAddBean dataBean = new CityAddBean(location, cond_txt, tmp_min, tmp_max);
            list.add(dataBean);
        }

        cursor.close();
        return list;
    }
}