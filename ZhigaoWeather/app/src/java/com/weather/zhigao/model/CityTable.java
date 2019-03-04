package com.weather.zhigao.model;

public class CityTable {

    public static final String TAB_NAME = "table_city";
    public static final String COL_LOC = "location";
    public static final String COL_COND = "cond_txt";
    public static final String COL_TMP_MIN = "tmp_min";
    public static final String COL_TMP_MAX = "tmp_max";
    public static final String CREATE_TABLE = "create table " + TAB_NAME + " ("
            + COL_LOC + " text primary key,"
            + COL_COND + " text,"
            + COL_TMP_MIN + " text,"
            + COL_TMP_MAX + " text"
            + ")";
}
