package com.example.myapplication.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.Araba

@Dao
interface ArabaDAO {
    @Insert
    suspend fun insertAll(vararg araba: Araba):List<Long>

    @Query("SELECT *FROM araba")
    suspend fun getAllAraba():List<Araba>

    @Query("SELECT*FROM araba WHERE uuid= :arabaId")
    suspend fun getAraba(arabaId:Int):Araba

    @Query("DELETE FROM araba")
    suspend fun deleteAllAraba()

    @Query("DELETE FROM araba WHERE uuid = :arabaId")  // id'ye göre araba sil
    suspend fun deleteAraba(arabaId: Int): Int
}