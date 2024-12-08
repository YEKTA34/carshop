package com.example.myapplication.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Araba
import com.example.myapplication.roomdb.ArabaDatabase
import kotlinx.coroutines.launch

class SepetViewModel(application: Application): AndroidViewModel(application) {

    val arabaListesi = MutableLiveData<List<Araba>>()

    // Sepet arabalarını al
    fun alSepetArabalar() {
        viewModelScope.launch {
            val dao = ArabaDatabase(getApplication()).arabaDao()
            val arabalar = dao.getAllAraba() // Tüm arabaları al
            arabaListesi.value = arabalar

        }
    }
}
