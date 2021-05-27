package com.diegolima.elocations.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.elocations.R
import com.diegolima.elocations.service.constants.DadosConstants
import com.diegolima.elocations.view.adapter.DadosAdapter
import com.diegolima.elocations.view.form.DadosFormActivity
import com.diegolima.elocations.view.listener.DadosListener
import androidx.lifecycle.Observer
import com.diegolima.elocations.viewmodel.DadosViewModel

class FormFragment : Fragment() {

    //TODO: por hora vamos deixar com o modelo de carregamento da @DadosViewModel -> mas
    //TODO: quando for fazer a Navegation bar da tela de cadastro, vai ser preciso implementar uma nova para imagens porque os dados é diferente da tela de dados
    //TODO: o ModelView de dados é a atual, mas o de imagens sera modelado, por hora deixando as classes visiveis para estruturacao do app
    //TODO: e para ja ir idealizando a producao final do mockup

    private lateinit var mViewModel: DadosViewModel
    private val mAdapter: DadosAdapter = DadosAdapter()
    private lateinit var mListener: DadosListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProvider(this).get(DadosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_form, container, false)

        //RecyclerView
        //1
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_form)
        //2
        recycler.layoutManager = LinearLayoutManager(context)
        //3
        recycler.adapter = mAdapter

        mListener = object : DadosListener {

            override fun onClick(id: Int) {
                val intent = Intent(context, DadosFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(DadosConstants.DADOSID, id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(DadosConstants.FILTER.FORM)
            }
        }

        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(DadosConstants.FILTER.FORM)
    }

    private fun observer() {
        //viewLifecycler no lugar de this se fosse um fragment ao invés de uma activity
        mViewModel.dadosList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateDados(it)
        })
    }

}