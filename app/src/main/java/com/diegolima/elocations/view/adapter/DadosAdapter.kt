package com.diegolima.elocations.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.diegolima.elocations.R
import com.diegolima.elocations.service.model.DadosModel
import com.diegolima.elocations.view.viewholder.DadosViewHolder

class DadosAdapter() : Adapter<DadosViewHolder>() {

    private var mDadosList:List<DadosModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DadosViewHolder {

        //modelo de estrutura de dados
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_estabelecimento, parent, false)

        return DadosViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mDadosList.count()
    }

    override fun onBindViewHolder(holder: DadosViewHolder, position: Int) {
        holder.bind(mDadosList[position])
    }

    fun updateDados(list: List<DadosModel>){
        mDadosList = list
        notifyDataSetChanged()
    }

}