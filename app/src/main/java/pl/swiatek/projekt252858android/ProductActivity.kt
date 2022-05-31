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
        productName.text=intent.getStringExtra("Product")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun detailsClicked(view: View){
        val intent=Intent(this,DetailsActivity::class.java).apply {
            putExtra("Details","Detaliki")
        }
        startActivity(intent)
    }

}