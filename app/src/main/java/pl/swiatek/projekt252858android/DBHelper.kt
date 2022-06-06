package pl.swiatek.projekt252858android

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context,"PRODUCTSDB",null,3) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE PRODUCTS(PRODUCTID INTEGER PRIMARY KEY AUTOINCREMENT,CODE TEXT,NAME TEXT, PRICE REAL,DETAILSID INTEGER)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE,DETAILSID) VALUES ('1','Chlebek',2.99,1)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE,DETAILSID) VALUES ('2','Bułka',0.99,2)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE,DETAILSID) VALUES ('3','Kanapka z boczkiem',6.99,3)")

        db?.execSQL("CREATE TABLE DETAILS(DETAILSID INTEGER PRIMARY KEY AUTOINCREMENT,COUNTRY TEXT,KCAL INTEGER)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Polska',412)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Polska',211)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Hiszpania',400)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS PRODUCTS")
        db?.execSQL("DROP TABLE IF EXISTS DETAILS")
        onCreate(db)
    }
}