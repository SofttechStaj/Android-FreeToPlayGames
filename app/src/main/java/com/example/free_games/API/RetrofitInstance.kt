package com.example.free_games.API

import com.example.free_games.Utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RetrofitInterface by lazy {
        retrofit.create(RetrofitInterface::class.java)
    }

}










//private val retrofit by lazy {
//    Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//}
//
//val api: SimpleApi by lazy {
//    retrofit.create(SimpleApi::class.java)
//}
