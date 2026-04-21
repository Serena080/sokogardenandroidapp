package com.example.sokogarden

import android.R.attr.data
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


//        Find the views by use of their names

        val txtname = findViewById<TextView>(R.id.txtProductName)
        val txtcost = findViewById<TextView>(R.id.txtProductCost)
        val imgProduct = findViewById<ImageView>(R.id.imgProduct)
        val txtdescription = findViewById<TextView>(R.id.description)


//        Retrieve the data passed from the previous activity

        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost",0)
        val product_photo= intent.getStringExtra("product_photo")
        val product_description = intent.getStringExtra("product_description")


//        Update the textviews with the data passed from the previous activity
        txtname.text = name
        txtcost.text = "KSH $cost"
        txtdescription.text = product_description



//        Specify the image url
        val imageUrl = "https://serena080.alwaysdata.net/static/images/$product_photo"

//Load image using Glide, Load Faster with Glide
        Glide.with(this)
            .load(imageUrl )
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

//        Find  the edittext and the pay now button by use of their ids

        val phone = findViewById<EditText>(R.id.phone)
        val btnPay = findViewById<Button>(R.id.pay)

//        Set click listener on the button
        btnPay.setOnClickListener {
//            Specify the url
            var url = "https://serena080.alwaysdata.net/api/mpesa_payment"


            val data = RequestParams()


//            insert data into the request params

            data.put("amount",cost)
            data.put("description",product_description)
            data.put("phone",phone.text.toString().trim())


//            Import the helper class
            val helper = ApiHelper(applicationContext)


//            Access the post inside of the helper class

            val api = ""
            helper.post(api,data)

//            Clear the phone entered


            phone.text.clear()

        }
    }


}



