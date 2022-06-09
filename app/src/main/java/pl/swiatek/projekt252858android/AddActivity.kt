package pl.swiatek.projekt252858android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.sql.SQLException

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        supportActionBar?.hide()
        //Toast.makeText(applicationContext,intent.getStringExtra("code"),Toast.LENGTH_SHORT).show()
    }
    fun addToDatabaseClick(view: View){
        val name=findViewById<EditText>(R.id.productNameAdd)
        val price=findViewById<EditText>(R.id.productPriceAdd)
        val country=findViewById<EditText>(R.id.productCountryAdd)
        val kcal=findViewById<EditText>(R.id.productKcalAdd)
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        if(!(kcal.text.isBlank()||price.text.isBlank()||country.text.isBlank()||kcal.text.isBlank())) {
            try {
                db.execSQL(
                    "INSERT INTO PRODUCTS(CODE, NAME, PRICE) VALUES ('" + intent.getStringExtra(
                        "code"
                    ) + "','" + name.text + "'," + price.text + ")"
                )
                db.execSQL("INSERT INTO DETAILS(COUNTRY, KCAL) VALUES ('" + country.text + "'," + kcal.text + ")")
                Toast.makeText(applicationContext, "Product Added", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: SQLException) {
                Toast.makeText(applicationContext, "Wrong Data", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext,"Fill data!",Toast.LENGTH_SHORT).show()
        }
    }
}