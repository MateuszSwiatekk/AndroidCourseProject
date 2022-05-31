package pl.swiatek.projekt252858android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val detailsText=findViewById<TextView>(R.id.detailsText)
        detailsText.text=intent.getStringExtra("Details")
    }

    fun scanMoreClicked(view: View){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}