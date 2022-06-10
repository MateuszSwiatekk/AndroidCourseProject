package pl.swiatek.projekt252858android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class EditPriceActivity : AppCompatActivity() {
    private lateinit var newPrice:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_price)
        supportActionBar?.hide()

        newPrice=findViewById<EditText>(R.id.EditedPriceText)
    }

    fun EditClicked(view: View){
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        if(!newPrice.text.isBlank()) {
            db.execSQL("UPDATE PRODUCTS SET PRICE = "+newPrice.text+" WHERE PRODUCTID = "+intent.getStringExtra("id"))
            Toast.makeText(applicationContext,"Price changed",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext,"Incorrect data",Toast.LENGTH_SHORT).show()
        }
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}