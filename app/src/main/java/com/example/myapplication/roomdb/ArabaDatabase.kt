package com.example.myapplication.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.model.Araba

@Database(entities = [Araba::class], version = 3)
abstract class  ArabaDatabase:RoomDatabase() {
    abstract fun arabaDao():ArabaDAO
    companion object{
        @Volatile
        private var instance:ArabaDatabase?=null
        private val lock=Any()
        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?:databaseOlustur(context).also{
                instance=it
            }
        }
        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ArabaDatabase::class.java, "aarabadatabase"
        ).addMigrations(MIGRATION_2_3)
            .build()

            private val MIGRATION_2_3=object :Migration(2,3){
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE Araba ADD COLUMN yeniSutun TEXT NOT NULL DEFAULT ''")
                }
            }
        }
    }

