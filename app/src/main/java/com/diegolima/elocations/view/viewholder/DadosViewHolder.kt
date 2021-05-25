package com.diegolima.elocations.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.elocations.R
import com.diegolima.elocations.service.model.DadosModel

class DadosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(dados: DadosModel){
       //tipo de dado dentro da estrutura adaptada
        val text_estabelecimento = itemView.findViewById<TextView>(R.id.text_estabelecimento)
        text_estabelecimento.text = dados.name
    }

}