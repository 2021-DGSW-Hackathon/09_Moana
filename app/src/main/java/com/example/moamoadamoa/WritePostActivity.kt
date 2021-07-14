package com.example.moamoadamoa

import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import java.lang.Exception
import java.net.URI

class WritePostActivity : AppCompatActivity() {
    private var productList = Product()
    private val OPEN_GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_post)


        val btnPhoto:ImageView = findViewById(R.id.btn_photo)
        btnPhoto.setOnClickListener {
            val intent:Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,OPEN_GALLERY)


        }

        val btnCheck:ImageButton = findViewById(R.id.btn_check)
        val editTitle:EditText = findViewById(R.id.edit_title)
        val editPrice:EditText = findViewById(R.id.edit_price)
        val editDetail:EditText = findViewById(R.id.edit_detil)
        btnCheck.setOnClickListener{
            val productTitle:String = editTitle.text.toString()
            val productPrice:String = editPrice.text.toString()
            val productDetail:String = editDetail.text.toString()
            productList.title = productTitle
            productList.price = productPrice
            productList.detail = productDetail



            val nextIntent = Intent(this, MainActivity::class.java)
            val pdlist: Product = productList
            nextIntent.putExtra("title",productList.title)
            nextIntent.putExtra("price",productList.price)
            nextIntent.putExtra("detail",productList.detail)
            nextIntent.putExtra("img",productList.img)

            startActivity(nextIntent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                var currentImageUrl: Uri? = data?.data

                val uri:Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver,currentImageUrl)
                    val btnPhoto:ImageView = findViewById(R.id.btn_photo)
                    Glide.with(this).load(uri).into(btnPhoto)
                    productList.img = uri
//                    btnPhoto.setImageBitmap(bitmap)
                }catch (e:Exception){

                }
            }
        }
    }
}