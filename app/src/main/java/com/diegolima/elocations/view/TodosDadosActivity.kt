package com.diegolima.elocations.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.elocations.R
import com.diegolima.elocations.view.adapter.DadosAdapter
import com.diegolima.elocations.viewmodel.TodosDadosViewModel
import kotlinx.android.synthetic.main.activity_dados_form.*
import kotlinx.android.synthetic.main.activity_todos_dados.*

class TodosDadosActivity : AppCompatActivity() {

    private lateinit var todosDadosViewModel: TodosDadosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos_dados)

        supportActionBar!!.hide()

        toobarTodosDados.setNavigationIcon(getDrawable(R.drawable.ic_arrow))
        toobarTodosDados.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        todosDadosViewModel = ViewModelProvider(this).get(TodosDadosViewModel::class.java)

        //RecyclerView
        //1
        val recycler = findViewById<RecyclerView>(R.id.recyclerView_todos_dados)
        //2
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        //3
        recycler.adapter = DadosAdapter()
    }
}