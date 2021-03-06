package com.diegolima.elocations.view.form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.diegolima.elocations.viewmodel.DadosFormViewModel
import com.diegolima.elocations.R
import com.diegolima.elocations.service.constants.DadosConstants
import com.diegolima.elocations.view.MainActivity
import kotlinx.android.synthetic.main.activity_dados_form.*
import kotlinx.android.synthetic.main.activity_main.*

class DadosFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: DadosFormViewModel
    private var mDadosId: Int = 0

    //localizacao atual
    private val localAtual: Double = 00.00000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_form)

        toobarDadosForm.title = "eLocations"
        setSupportActionBar(toobarDadosForm)

        toobarDadosForm.navigationIcon = getDrawable(R.drawable.ic_arrow)
        toobarDadosForm.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        mViewModel = ViewModelProvider(this).get(DadosFormViewModel::class.java)

        setListeners()
        observe()
        loadData()
        loadSpinner()
    }

    private fun setListeners() {
        button_dados.setOnClickListener(this)
        button_imagens.setOnClickListener(this)
        button_save.setOnClickListener(this)
        button_delete_establishment.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_dados -> {
                Toast.makeText(
                    this,
                    "Você já está na tela de cadastro dos dados",
                    Toast.LENGTH_LONG
                ).show()
            }
            R.id.button_imagens -> {
                startActivity(Intent(this, ImagensFormActivity::class.java))
                //TODO: a questao aqui é se automatiza os dados apos setar a/as imagem/ns
                //TODO: se o contexto vai estar ativo ou se vai ter q passar (pq creio que vai perder a referencia atual,
                //TODO: a nao ser q use uma instancia unica)
            }
            //TODO: ver possibilidade de Snack
            R.id.button_save -> {

                val name = edit_name.text.toString()
                val description = edit_description.text.toString()

                mViewModel.save(mDadosId, name, description)

            }
            R.id.button_delete_establishment -> {
                Toast.makeText(this, "Estabelecimento excluido com sucesso!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mDadosId = bundle.getInt(DadosConstants.DADOSID)
            //carregar
            mViewModel.load(mDadosId)
        }
    }

    private fun observe() {
        mViewModel.saveDados.observe(this, Observer {
            if (it) {
                //Toast.makeText(applicationContext, "Sucesso", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Estabelecimento Salvo com sucesso!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Falha", Toast.LENGTH_SHORT).show()
            }
            finish()
        })
        mViewModel.dados.observe(this, Observer {
            edit_name.setText(it.name)
            edit_description.setText(it.description)
        })
    }

    private fun loadSpinner() {
        val mList = listOf(
            "Restaurante",
            "Lanchonete",
            "Ponto de Ônibus",
            "Escola",
            "Posto de Gasolina",
            "Supermercado",
            "Hotel",
            "Loja",
            "Spa"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mList)
        spinner_dinamic.adapter = adapter
    }
}