package com.diegolima.elocations.view.form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.elocations.R
import com.diegolima.elocations.view.MainActivity
import com.diegolima.elocations.view.adapter.TabbebFormAdapter
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        toolBarForm.title = "eLocations"
        setSupportActionBar(toolBarForm)

        toolBarForm.navigationIcon = getDrawable(R.drawable.ic_arrow)
        toolBarForm.setNavigationOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        val fragmentAdapter = TabbebFormAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tablayout.setupWithViewPager(viewPager)
    }
}