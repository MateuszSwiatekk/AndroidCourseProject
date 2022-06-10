package pl.swiatek.projekt252858android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ProductActivity : AppCompatActivity() {
    private lateinit var productName:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        supportActionBar?.hide()
        productName=findViewById<TextView>(R.id.productName)
        val productPrice=findViewById<TextView>(R.id.productPrice)
        productName.text=intent.getStringExtra("Product")
        productPrice.text=intent.getStringExtra("Price")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun detailsClicked(view: View){
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        var query=db.rawQuery("SELECT * FROM PRODUCTS WHERE NAME = '"+productName.text+"'",null)
        if(query.moveToNext()) {
            var detailsId = query.getString(0)
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("Details", detailsId)
            }
            startActivity(intent)
        }
    }

    fun editPriceClicked(view: View){
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        var query=db.rawQuery("SELECT * FROM PRODUCTS WHERE NAME = '"+productName.text+"'",null)
        if(query.moveToNext()) {
            val intent = Intent(this, EditPriceActivity::class.java).apply {
                putExtra("id",query.getString(0))
            }
            startActivity(intent)
            finish()
        }
    }
}