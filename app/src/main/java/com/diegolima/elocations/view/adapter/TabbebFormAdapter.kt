package com.diegolima.elocations.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.diegolima.elocations.view.form.FragmentDados
import com.diegolima.elocations.view.form.FragmentImagens

class TabbebFormAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            //estes metodos sao setados nas tabs
            0 -> {
                FragmentDados()
            }
            else -> {
                return FragmentImagens()
            }

        }
    }

    override fun getCount(): Int {
        return 2 //quantidade de tabs retornadas
    }

    //titulos
    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0-> "Dados"
            else -> {
                return "Imagens"
            }
        }
    }

}