package com.diegolima.elocations.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.diegolima.elocations.service.model.DadosModel
import com.diegolima.elocations.service.repository.DadosRepository

class DadosFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mDadosRepository: DadosRepository = DadosRepository.getInstance(mContext)

    private var mSaveDados = MutableLiveData<Boolean>()
    val saveDados: LiveData<Boolean> = mSaveDados

    private var mDados = MutableLiveData<DadosModel>()
    val dados: LiveData<DadosModel> = mDados

    //TODO: falta implementar tipo de estabelecimento e localização do estabelecimento
    fun save(id: Int, name: String, description: String) {
        val dados = DadosModel(id, name, description)

        if (id == 0) {
            mSaveDados.value = mDadosRepository.save(dados)
        } else {
            mSaveDados.value = mDadosRepository.update(dados)
        }

    }

    fun load(id: Int) {
        mDados.value = mDadosRepository.get(id)
    }
}