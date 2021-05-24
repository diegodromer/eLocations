package com.diegolima.elocations.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.elocations.R

class TodosDadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos_dados)

        supportActionBar!!.hide()

    }


}