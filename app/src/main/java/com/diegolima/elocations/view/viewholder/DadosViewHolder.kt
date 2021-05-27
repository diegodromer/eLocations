package com.diegolima.elocations.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.elocations.R
import com.diegolima.elocations.service.model.DadosModel
import com.diegolima.elocations.view.listener.DadosListener

class DadosViewHolder(itemView: View, private val listener: DadosListener) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(dados: DadosModel) {
        //tipo de dado dentro da estrutura adaptada
        val text_name = itemView.findViewById<TextView>(R.id.text_name)
        text_name.text = dados.name

        //TODO: tomar cuidado se der erro, se não é a linha view adicionada na recycler que pode estar influenciando

        text_name.setOnClickListener {
            listener.onClick(dados.id)
        }

        text_name.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_estabelecimento)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { dialog, witch ->
                    listener.onDelete(dados.id)
                }
                .setNegativeButton(R.string.cancelar, null)
                .show()
            true
        }
    }

}