package hu.bme.aut.fitguru.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hu.bme.aut.fitguru.fragments.ProfileInfoFragment
import hu.bme.aut.fitguru.fragments.ProfileSettingsFragment

class PersonalPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment = when(position){
        0 -> ProfileInfoFragment()
        1 -> ProfileSettingsFragment()
        else -> ProfileInfoFragment()
    }

    override fun getCount() : Int = NUM_PAGES

    companion object{
        const val NUM_PAGES = 2
    }
}