package com.diegolima.elocations.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.elocations.R
import kotlinx.android.synthetic.main.activity_dados_form.*
import kotlinx.android.synthetic.main.activity_todos_dados.*

class TodosDadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos_dados)

        supportActionBar!!.hide()

        toobarTodosDados.setNavigationIcon(getDrawable(R.drawable.ic_arrow))
        toobarTodosDados.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}