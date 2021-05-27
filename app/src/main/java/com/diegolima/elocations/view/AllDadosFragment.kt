package com.diegolima.elocations.view

import android.content.Intent
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
import com.diegolima.elocations.service.constants.DadosConstants
import com.diegolima.elocations.view.adapter.DadosAdapter
import com.diegolima.elocations.view.form.DadosFormActivity
import com.diegolima.elocations.view.listener.DadosListener
import com.diegolima.elocations.viewmodel.DadosViewModel

class AllDadosFragment : Fragment() {

    private lateinit var mViewModel: DadosViewModel
    private val mAdapter: DadosAdapter = DadosAdapter()
    private lateinit var mListener: DadosListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ViewModelProvider(this).get(DadosViewModel::class.java).also { mViewModel = it }

        val root = inflater.inflate(R.layout.fragment_all, container, false)

        //RecyclerView
        //1
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_dados)
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
                mViewModel.load(DadosConstants.FILTER.EMPTY)
            }
        }

        mAdapter.attachListener(mListener)

        observer()

        return root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(DadosConstants.FILTER.EMPTY)
    }

    private fun observer() {
        //viewLifecycler no lugar de this se fosse um fragment ao invés de uma activity
        mViewModel.dadosList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateDados(it)
        })
    }
}