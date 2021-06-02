package com.diegolima.elocations.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.elocations.R
import kotlinx.android.synthetic.main.activity_all_dados.*
import kotlinx.android.synthetic.main.activity_main.*

class AllDadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_dados)

        toolBarAllDados.setTitle("eLocations")
        setSupportActionBar(toolBarAllDados)

/*        toolBarAllDados.setNavigationIcon(getDrawable(R.drawable.ic_arrow))

        toolBarAllDados.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }*/
    }
}