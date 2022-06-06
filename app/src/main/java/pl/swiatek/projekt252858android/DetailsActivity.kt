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

        val detailsNameText=findViewById<TextView>(R.id.detailsNameText)
        val data=intent.getStringExtra("Details")
        detailsNameText.text=data



    }

    fun scanMoreClicked(view: View){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}