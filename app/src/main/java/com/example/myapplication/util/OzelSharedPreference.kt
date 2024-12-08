package com.example.myapplication.util

import android.content.Context
import android.content.SharedPreferences

class OzelSharedPreference {
    companion object{
        private val TIME="time"
        private var sharedPreferences:SharedPreferences?=null
        @Volatile
        private var instance:OzelSharedPreference?=null
        private val lock=Any()
        operator fun invoke(context: Context):OzelSharedPreference= instance?: synchronized(lock){
            instance?:ozelSharedPreferenceOlustur(context).also{
            instance=it

            }
        }
        private fun ozelSharedPreferenceOlustur(context: Context):OzelSharedPreference{
            sharedPreferences=androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
            return OzelSharedPreference()
        }

}
    fun zamaniKaydet(zaman:Long){
        sharedPreferences?.edit()?.putLong(TIME,zaman)?.apply()
    }
    fun zamaniAl()= sharedPreferences?.getLong(TIME,0)
}