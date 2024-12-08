package com.example.myapplication.service

import com.example.myapplication.model.Araba
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ArabaAPIServis {
    private val BASE_URL = "https://raw.githubusercontent.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ArabaAPI::class.java)

    suspend fun getData() : List<Araba> {
        return api.getAraba()
    }
}
