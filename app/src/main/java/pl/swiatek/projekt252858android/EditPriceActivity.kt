package pl.swiatek.projekt252858android

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class EditPriceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_price)
    }

    fun EditClicked(view: View){
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        db.execSQL("UPDATE PRODUCTS SET PRICE = 19.99 WHERE PRODUCTID = 2")
        finish()
    }
}