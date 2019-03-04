package com.weather.zhigao.db

import android.content.ContentValues
import android.content.Context

import com.weather.zhigao.model.WeatherForecastEntity
import com.weather.zhigao.model.WeatherForecastEntity.HeWeather6Bean.DailyForecastBean
import com.weather.zhigao.model.CityAddBean
import com.weather.zhigao.model.CityTable
import com.weather.zhigao.utils.LogUtil

import java.util.ArrayList

/**
 * 使用SQL语句增删改查
 */

class DatabaseManager private constructor(context: Context) {
    internal var TAG = "DatabaseManager"
    var mContext: Context = context;

    //    public DatabaseManager(Context context) {
    //        mDbHelper = new MyDBHelper(context);
    //    }

    /**
     * 插入
     *
     * @param dataBean
     */
    fun insert(dataBean: CityAddBean) {
        val db = mDbHelper!!.writableDatabase
        // 执行添加语句
        val values = ContentValues()
        values.put(CityTable.COL_LOC, dataBean.location)
        values.put(CityTable.COL_COND, dataBean.cond_txt)
        values.put(CityTable.COL_TMP_MIN, dataBean.tmp_min)
        values.put(CityTable.COL_TMP_MAX, dataBean.tmp_max)

        db.insert(CityTable.TAB_NAME, null, values)
    }

    /**
     * 更新
     *
     * @param location
     * @param dataBean
     */
    fun update(location: String, dataBean: CityAddBean) {
        val db = mDbHelper!!.writableDatabase
        val values = ContentValues()
        values.put(CityTable.COL_LOC, dataBean.location)
        values.put(CityTable.COL_COND, dataBean.cond_txt)
        values.put(CityTable.COL_TMP_MIN, dataBean.tmp_min)
        values.put(CityTable.COL_TMP_MAX, dataBean.tmp_max)

        db.update(CityTable.TAB_NAME, values, CityTable.COL_LOC + "=?", arrayOf(location))
    }

    /**
     * 根据id删除
     */
    fun delete(location: String) {
        val db = mDbHelper!!.writableDatabase
        db.delete(CityTable.TAB_NAME, CityTable.COL_LOC + "=?", arrayOf(location))
    }

    /**
     * 删除所有
     */
    fun deleteAll() {
        val db = mDbHelper!!.writableDatabase
        db.delete(CityTable.TAB_NAME, null, null)
    }


    /**
     * 查询所有
     */
    fun queryAll(): MutableList<CityAddBean> {

        val list = ArrayList<CityAddBean>()
        try {
            val db = mDbHelper!!.writableDatabase

            val sql = "select * from " + CityTable.TAB_NAME
            val cursor = db.rawQuery(sql, null)
            while (cursor.moveToNext()) {

                val location = cursor.getString(cursor.getColumnIndex(CityTable.COL_LOC))
                val cond_txt = cursor.getString(cursor.getColumnIndex(CityTable.COL_COND))
                val tmp_min = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MIN))
                val tmp_max = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MAX))

                val dataBean = CityAddBean(location, cond_txt, tmp_min, tmp_max)
                list.add(dataBean)
            }

            cursor.close()
        } catch (e: Exception) {
            LogUtil.d(TAG, "e.getMessage()：" + e.message)
        }

        return list
    }

    /**
     * 根据id查询
     */
    fun query(loc: String): List<CityAddBean> {
        val list = ArrayList<CityAddBean>()
        val db = mDbHelper!!.writableDatabase
        val sql = "select * from " + CityTable.TAB_NAME + " where " + CityTable.COL_LOC + " =?"
        val cursor = db.rawQuery(sql, arrayOf(loc))

        while (cursor.moveToNext()) {
            val location = cursor.getString(cursor.getColumnIndex(CityTable.COL_LOC))
            val cond_txt = cursor.getString(cursor.getColumnIndex(CityTable.COL_COND))
            val tmp_min = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MIN))
            val tmp_max = cursor.getString(cursor.getColumnIndex(CityTable.COL_TMP_MAX))

            val dataBean = CityAddBean(location, cond_txt, tmp_min, tmp_max)
            list.add(dataBean)
        }

        cursor.close()
        return list
    }

    fun getCityBean(weatherBroadcast: WeatherForecastEntity): CityAddBean {
        val location = weatherBroadcast.HeWeather6!![0].basic.location
        val cond_txt = weatherBroadcast.HeWeather6!![0].now.cond_txt
        val bean = weatherBroadcast.HeWeather6!![0].daily_forecast[0]
        val tmp_min = bean.tmp_min
        val tmp_max = bean.tmp_max
        return CityAddBean(location, cond_txt, tmp_min, tmp_max)
    }

    companion object {
//        private var instance: MyDBHelper? = null
//            get() {
//                if (field == null) {
//                    field = MyDBHelper()
//                }
//                return field
//            }
//        @Synchronized
//        fun get(): MyDBHelper{
//            return instance!!
//        }

        private var mDbHelper: MyDBHelper? = null
        private  var instance: DatabaseManager?=null

        fun getInstance(context: Context): DatabaseManager? {

            if (instance == null) {
                synchronized(DatabaseManager::class) {
                    instance = DatabaseManager(context)
                    mDbHelper = MyDBHelper(context)
                }
            }
            return instance
        }
    }
}