package hu.bme.aut.fitguru.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hu.bme.aut.fitguru.fragments.Details1Fragment
import hu.bme.aut.fitguru.fragments.Details2Fragment


class DietPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment = when(position){
            0 -> Details2Fragment()
            1 -> Details1Fragment()
            else -> Details2Fragment()
        }

        override fun getCount() : Int = NUM_PAGES

        companion object{
            const val NUM_PAGES = 2
        }
    }
