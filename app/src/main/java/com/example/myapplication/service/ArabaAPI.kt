package com.example.myapplication.service

import com.example.myapplication.model.Araba
import okhttp3.Response
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ArabaAPI {


    @GET("https://raw.githubusercontent.com/YEKTA34/araba_____json/refs/heads/main/ARABA_JSON.json")
    suspend fun getAraba():List<Araba>
    @DELETE("arabalar/{id}")
    suspend fun deleteAraba(@Path("id") arabaId: Int): Unit
}