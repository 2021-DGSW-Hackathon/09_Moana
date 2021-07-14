package com.example.moamoadamoa

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable
import java.net.URI

private var i = 0
private val productList = arrayListOf<Product>()
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val recyclerProduct: RecyclerView = findViewById(R.id.rv_card)
        val layoutInflater = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false).also { recyclerProduct.layoutManager = it }
        CardViewDrawing(recyclerProduct, LayoutInflater.from(this@MainActivity), productList)
        val userinfor:ImageView = findViewById(R.id.img_userinfor)
        userinfor.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }

        val home:ImageView = findViewById(R.id.img_home)
        home.setOnClickListener {
//            Toast.makeText(this.applicationContext,"현재 홈 화면 입니다.",Toast.LENGTH_SHORT).show()
        }

        val productAdd:ImageView = findViewById(R.id.img_plus)

        productAdd.setOnClickListener {
            val intent = Intent(this, WritePostActivity::class.java)
            startActivity(intent)
        }
        val pdtitle: String? = intent.getStringExtra("title")
        val pdprice: String? = intent.getStringExtra("price")
        val pddetail: String? = intent.getStringExtra("detail")
        val pdimg: Uri? = getIntent().getParcelableExtra("img")
        val list: Product = Product(
            pdtitle, pdprice, pddetail, pdimg
        )

        productList.add(list)

        i++

    }
    fun CardViewDrawing(recyclerView: RecyclerView, inflater: LayoutInflater, result:ArrayList<Product>){
        val adapter = ProductAdapter(result,inflater, activity = this, )
        recyclerView.adapter = adapter
    }
}


class ProductAdapter(
    val productList: ArrayList<Product>,
    val inflater: LayoutInflater,
    val activity:MainActivity
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val img: ImageView
        val title: TextView
        val price: TextView

        init {
            itemView.setOnClickListener{
                val intent = Intent(activity, DetailActivity::class.java)
                activity.startActivity(intent)
            }
            img = itemView.findViewById(R.id.img_intro)
            title = itemView.findViewById(R.id.product_title)
            price = itemView.findViewById(R.id.product_price)
        }


    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.recycler_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageURI(productList.get(position).img ?: null)
        holder.title.setText(productList.get(position).title)
        holder.price.setText(productList.get(position).price)
    }
}