package pl.swiatek.projekt252858android

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context,"PRODUCTSDB",null,6) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE PRODUCTS(PRODUCTID INTEGER PRIMARY KEY AUTOINCREMENT,CODE TEXT,NAME TEXT, PRICE REAL)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('1','Chleb Wiejski',2.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('2','Bułka Grahamka',0.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('3','Kanapka z szynką',6.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('4','Zapiekanka z serem',7.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('5','Zapiekanka z serem i pieczarkami',8.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('6','Zapiekanka z salami, serem i pieczarkami',9.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('7','Margaryna',4.49)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('8','Skyr',2.85)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('9','Pepsi 1.75L',5.99)")
        db?.execSQL("INSERT INTO PRODUCTS(CODE,NAME, PRICE) VALUES ('10','Sznycelki',12.99)")

        db?.execSQL("CREATE TABLE DETAILS(DETAILSID INTEGER PRIMARY KEY AUTOINCREMENT,COUNTRY TEXT,KCAL INTEGER)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Polska',629)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Polska',109)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Polska',310)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Niemcy',629)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Niemcy',109)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Niemcy, Włochy',310)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Węgry',629)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Norwegia',109)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('USA',310)")
        db?.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('Austria',629)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS PRODUCTS")
        db?.execSQL("DROP TABLE IF EXISTS DETAILS")
        onCreate(db)
    }
}