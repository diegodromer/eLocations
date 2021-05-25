package com.diegolima.elocations.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegolima.elocations.service.model.DadosModel
import com.diegolima.elocations.service.repository.DadosRepository

class DadosViewModel (application: Application): AndroidViewModel(application) {

    private val mDadosRepository = DadosRepository.getInstance(application.applicationContext)

    private val mDadosList = MutableLiveData<List<DadosModel>>()
    val dadosList:LiveData<List<DadosModel>> = mDadosList

    fun load() {
        mDadosList.value = mDadosRepository.getAll()
    }

}