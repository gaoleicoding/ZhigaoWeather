package com.weather.zhigao.model

object CityTable {

    val TAB_NAME = "table_city"
    val COL_LOC = "location"
    val COL_COND = "cond_txt"
    val COL_TMP_MIN = "tmp_min"
    val COL_TMP_MAX = "tmp_max"
    val CREATE_TABLE = ("create table " + TAB_NAME + " ("
            + COL_LOC + " text primary key,"
            + COL_COND + " text,"
            + COL_TMP_MIN + " text,"
            + COL_TMP_MAX + " text"
            + ")")
}
