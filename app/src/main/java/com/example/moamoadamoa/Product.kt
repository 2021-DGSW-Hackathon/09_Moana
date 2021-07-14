package com.example.moamoadamoa

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import retrofit2.http.Url
import java.io.Serializable
import java.net.URI

class Product(

    var title: String? = null,
    var price: String? = null,
    var detail: String? = null,
    var img: Uri? = null,
):Serializable