package pl.swiatek.projekt252858android

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context,"PRODUCTSDB",null,2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE PRODUCTS(PRODUCTID INTEGER PRIMARY KEY AUTOINCREMENT,CODE TEXT,NAME TEXT, PRICE REAL)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('1','Chlebek',2.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('2','Bułka',0.99)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}