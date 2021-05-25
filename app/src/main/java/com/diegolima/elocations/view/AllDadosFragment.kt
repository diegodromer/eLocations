package com.diegolima.elocations.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.elocations.R
import com.diegolima.elocations.view.adapter.DadosAdapter
import com.diegolima.elocations.viewmodel.DadosFormViewModel
import com.diegolima.elocations.viewmodel.DadosViewModel

class AllDadosFragment : Fragment() {

    private lateinit var mViewModel: DadosViewModel
    private val mAdapter: DadosAdapter = DadosAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(DadosViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_all, container, false)



        //RecyclerView
        //1
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_dados)
        //2
        recycler.layoutManager = LinearLayoutManager(context)
        //3
        recycler.adapter = mAdapter

        observer()

        mViewModel.load()

        return root
    }

    private fun observer() {
        //viewLifecycler no lugar de this se fosse um fragment ao invés de uma activity
        mViewModel.dadosList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateDados(it)
        })
    }
}