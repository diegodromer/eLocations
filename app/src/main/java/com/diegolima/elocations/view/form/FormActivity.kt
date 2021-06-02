package com.diegolima.elocations.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.elocations.R
import com.diegolima.elocations.view.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        toolBarForm.setTitle("Tab Layout")
        setSupportActionBar(toolBarForm)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentAdapter

        tablayout.setupWithViewPager(viewPager)
    }
}