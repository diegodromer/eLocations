package com.diegolima.elocations.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.diegolima.elocations.view.form.FragmentOne
import com.diegolima.elocations.view.form.FragmentTwo

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            //estes metodos sao setados nas tabs
            0 -> {
                FragmentOne()
            }
            else -> {
                return FragmentTwo()
            }

        }
    }

    override fun getCount(): Int {
        return 2 //quantidade de tabs retornadas
    }

    //titulos
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "One"
            else -> {
                return "Two"
            }
        }
    }

}