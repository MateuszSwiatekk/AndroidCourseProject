package pl.swiatek.projekt252858android

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode

private const val CAMERA_REQUEST_CODE=101

class MainActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner
    private lateinit var code:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        val productText=findViewById<TextView>(R.id.scanText)
        val questionText=findViewById<TextView>(R.id.questionText)
        val yesButton=findViewById<Button>(R.id.yesButton)
        val noButton=findViewById<Button>(R.id.noButton)
        val addProduct=findViewById<Button>(R.id.addProduct)
        val productsList=findViewById<Button>(R.id.button5)
        productsList.visibility=View.VISIBLE
        questionText.visibility= View.INVISIBLE
        yesButton.visibility= View.INVISIBLE
        noButton.visibility= View.INVISIBLE
        addProduct.visibility=View.INVISIBLE


        getPermissions()
        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                code=it.text
                var query=db.rawQuery("SELECT * FROM PRODUCTS WHERE CODE ="+it.text,null)
                if(query.moveToNext()) {
                    var description=query.getString(2)
                    productText.text=description
                }
                if(query.count>0) {
                    questionText.visibility = View.VISIBLE
                    yesButton.visibility = View.VISIBLE
                    noButton.visibility = View.VISIBLE
                }else{
                    productText.text="Product not found. Do you want to add it?"
                    questionText.visibility = View.INVISIBLE
                    yesButton.visibility = View.INVISIBLE
                    noButton.visibility = View.INVISIBLE
                    addProduct.visibility=View.VISIBLE
                }
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                //Toast.makeText(this, "Camera initialization error: ${it.message}",
                  //  Toast.LENGTH_LONG).show()\
                productText.text="Camera initialization error: "+it.message
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
            questionText.visibility = View.INVISIBLE
            yesButton.visibility = View.INVISIBLE
            noButton.visibility = View.INVISIBLE
            addProduct.visibility=View.INVISIBLE
            productText.text="Scanning ..."
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun getPermissions(){
        val permissions=ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        if (permissions != PackageManager.PERMISSION_GRANTED){
            requestingPermissions()
        }
    }

    private fun requestingPermissions(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode:Int,permissions:Array<out String>,grantResults:IntArray){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val productText=findViewById<TextView>(R.id.scanText)
        when(requestCode){
            CAMERA_REQUEST_CODE->{
                if(grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(applicationContext,"Camera permissions are necessary to use this app!",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext,"Permissions granted",Toast.LENGTH_SHORT).show()
                    productText.text="Scanning ..."

                }
            }
        }
    }

    fun noClicked(view: View){
        val questionText=findViewById<TextView>(R.id.questionText)
        val yesButton=findViewById<Button>(R.id.yesButton)
        val noButton=findViewById<Button>(R.id.noButton)
        val productText=findViewById<TextView>(R.id.scanText)

        codeScanner.startPreview()

        productText.text="Scanning ..."
        questionText.visibility= View.INVISIBLE
        yesButton.visibility= View.INVISIBLE
        noButton.visibility= View.INVISIBLE
    }

    fun yesClicked(view: View){
        var helper=DBHelper(applicationContext)
        var db=helper.readableDatabase
        var query=db.rawQuery("SELECT * FROM PRODUCTS WHERE NAME = '"+findViewById<TextView>(R.id.scanText).text+"'",null)
        if(query.moveToNext()) {
            var product = query.getString(2)
            var price=query.getString(3)
            val intent=Intent(this,ProductActivity::class.java).apply {
                putExtra("Product",product.toString())
                putExtra("Price",price)
            }
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    fun addClick(view: View){
        findViewById<Button>(R.id.addProduct).visibility=View.INVISIBLE
        findViewById<TextView>(R.id.scanText).text="Scanning..."
        val intent=Intent(this,AddActivity::class.java).apply {
            putExtra("code",code)
        }
        startActivity(intent)
    }

}
