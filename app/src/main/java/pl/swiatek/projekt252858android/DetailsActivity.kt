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

        val detailsId=intent.getStringExtra("Details")

        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        var query=db.rawQuery("SELECT * FROM DETAILS WHERE DETAILSID = "+detailsId,null)
        if(query.moveToNext()) {
            var country = query.getString(1)
            var kcal=query.getString(2)
            val detailsNameText=findViewById<TextView>(R.id.detailsNameText)
            val detailsCountryText=findViewById<TextView>(R.id.detailsPriceText)
            detailsNameText.text=kcal+" Kcal"
            detailsCountryText.text="Kraj: "+country

        }

    }

    fun scanMoreClicked(view: View){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}