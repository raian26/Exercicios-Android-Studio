package com.generation.exercicioviewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.generation.exercicioviewmodel.databinding.ActivityMainBinding

class MainViewModel : ViewModel() {
    private lateinit var binding: ActivityMainBinding
    var cont = MutableLiveData<Int>(0)

    fun addNum(){
        cont.value = cont.value?.plus(1)
    }
}
