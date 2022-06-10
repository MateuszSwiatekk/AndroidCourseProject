package pl.swiatek.projekt252858android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView

class ProductListActivity : AppCompatActivity() {
    private lateinit var productList:ListView
    private lateinit var adapter: ArrayAdapter<String>
    val mutableListProducts: MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        productList=findViewById<ListView>(R.id.listView1)
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            mutableListProducts
        )
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        var query=db.rawQuery("SELECT * FROM PRODUCTS",null)

        while (query.moveToNext()){
            mutableListProducts.add("Product name: "+query.getString(2)+"; Price: "+query.getString(3))
        }
        productList.adapter=adapter
    }

    fun findProductsClick(view: View){
        mutableListProducts.clear()

        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        var query2=db.rawQuery("SELECT * FROM PRODUCTS WHERE NAME LIKE '%"+findViewById<EditText>(R.id.editTextFilterName).text+"%'",null)

        while (query2.moveToNext()){
            mutableListProducts.add("Product name: "+query2.getString(2)+"; Price: "+query2.getString(3))
        }
        productList.adapter=adapter
        productList.invalidateViews()
    }

}