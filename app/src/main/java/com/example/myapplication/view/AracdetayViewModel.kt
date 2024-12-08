package com.example.myapplication.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Araba
import com.example.myapplication.roomdb.ArabaDatabase
import kotlinx.coroutines.launch
import java.util.UUID

class AracdetayViewModel(application: Application):AndroidViewModel(application) {
    val arabaLiveData= MutableLiveData<Araba>()
    fun roomVerisiniAl(uuid: Int){
        viewModelScope.launch {
            val dao=ArabaDatabase(getApplication()).arabaDao()
            val araba=dao.getAraba(uuid)
            arabaLiveData.value=araba
        }
    }
}