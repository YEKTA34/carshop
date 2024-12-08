package com.example.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Araba(
    val gorsel: String?,
    val fiyat: String?,
    val motorGuc: String?,
    val yakit: String?,
    val hp: String?,
    val marka: String?,
    val model: String?,


) {@PrimaryKey(autoGenerate = true)
var uuid:Int=0
}