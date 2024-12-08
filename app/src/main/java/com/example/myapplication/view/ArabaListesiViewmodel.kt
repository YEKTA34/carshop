package com.atilsamancioglu.besinkitabi.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Araba
import com.example.myapplication.roomdb.ArabaDatabase
import com.example.myapplication.service.ArabaAPIServis
import com.example.myapplication.util.OzelSharedPreference


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class ArabaListesiViewmodel(application: Application) : AndroidViewModel(application) {
    val arabalar = MutableLiveData<List<Araba>>()
    val arabaHataMesaji = MutableLiveData<Boolean>()
    val arabaYukleniyor = MutableLiveData<Boolean>()
    private var guncellemeZamani = 10 * 60 * 1000 * 1000 * 1000L

    private val ArabaApiServis = ArabaAPIServis()
    private val ozelSharedPreferences = OzelSharedPreference(getApplication())

    fun refreshData() {

        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani){
            //Sqlite'tan çek
            verileriSQLitetanAl()
        } else {
            verileriInternettenAl()
        }
    }

    fun refreshFromInternet(){
        verileriInternettenAl()
    }

    private fun verileriSQLitetanAl() {
        arabaYukleniyor.value = true

        viewModelScope.launch {

            val arabaListesi = ArabaDatabase(getApplication()).arabaDao().getAllAraba()
            arabalariGoster(arabaListesi)
            Toast.makeText(getApplication(),"Besinleri Room'dan Aldık",Toast.LENGTH_LONG).show()

        }

    }


    private fun verileriInternettenAl(){
        arabaYukleniyor.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val arabalar = ArabaApiServis.getData()
            withContext(Dispatchers.Main) {
                arabaYukleniyor.value = false
                sqliteSakla(arabalar)
                Toast.makeText(getApplication(),"Besinleri Internet'ten Aldık",Toast.LENGTH_LONG).show()
            }
        }


    }

    private fun arabalariGoster(arabalarListesi : List<Araba>){
        arabalar.value = arabalarListesi
        arabaHataMesaji.value = false
        arabaYukleniyor.value = false
    }

    private fun sqliteSakla(arabaListesi: List<Araba>){

        viewModelScope.launch {

            val dao = ArabaDatabase(getApplication()).arabaDao()
            dao.deleteAllAraba()
            val uuidListesi = dao.insertAll(*arabaListesi.toTypedArray())
            var i = 0
            while (i < arabaListesi.size){
                arabaListesi[i].uuid = uuidListesi[i].toInt()
                i = i + 1
            }
            arabalariGoster(arabaListesi)
        }

        ozelSharedPreferences.zamaniKaydet(System.nanoTime())

    }


}