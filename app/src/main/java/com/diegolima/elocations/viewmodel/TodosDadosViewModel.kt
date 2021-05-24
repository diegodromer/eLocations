package com.diegolima.elocations.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodosDadosViewModel: ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este é todos os dados"
    }
    val text:LiveData<String> = _text
}