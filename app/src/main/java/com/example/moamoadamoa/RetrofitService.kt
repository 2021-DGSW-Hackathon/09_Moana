package com.example.moamoadamoa

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {
    @GET("Any/")
    fun getProduct():Call<ArrayList<Product>>
}