package com.diegolima.elocations.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.elocations.R
import kotlinx.android.synthetic.main.activity_all_dados.*

class AllDadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_dados)

        supportActionBar!!.hide()

        toobarAllDados.setNavigationIcon(getDrawable(R.drawable.ic_arrow))

        toobarAllDados.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}