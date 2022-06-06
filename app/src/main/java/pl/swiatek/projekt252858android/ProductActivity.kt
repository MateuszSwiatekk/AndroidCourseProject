package pl.swiatek.projekt252858android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        Toast.makeText(applicationContext,"New activity",Toast.LENGTH_SHORT).show()
        val productName=findViewById<TextView>(R.id.productName)
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
        var query=db.rawQuery("SELECT * FROM PRODUCTS WHERE NAME = '"+findViewById<TextView>(R.id.productName).text+"'",null)
        if(query.moveToNext()) {
            var detailsId = query.getString(4)
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra("Details", detailsId)
            }
            startActivity(intent)
        }
    }

}