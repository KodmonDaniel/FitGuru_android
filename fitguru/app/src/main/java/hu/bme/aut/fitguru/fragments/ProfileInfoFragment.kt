package hu.bme.aut.fitguru.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.fitguru.databinding.ProfileInfoBinding

class ProfileInfoFragment : Fragment() {

    private lateinit var  binding: ProfileInfoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ProfileInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        setData()

    }


    private fun setData(){
        binding.tvName.text = getName()

        binding.tvBmi.text = bmiCalc().toString()
        binding.tvBfm.text = bfmCalc().toString()
        binding.tvOw.text = owCalc().toString()
        binding.tvBfp.text = bmpCalc().toString()

        binding.weightProgress.setProgress(getPercentage(owCalc(), getWeight().toDouble()))
    }

    private fun bmiCalc() : Double {
        val weight = getWeight().toDouble()
        val height = getHeight().toDouble() / 100
        return Math.round((weight / (height * height)) * 100.0) / 100.0
    }

    private fun bfmCalc() : Double {
        val bmp = bmpCalc()
        val weight = getWeight().toDouble()
        return Math.round(((weight * bmp) / 100) * 100.0) / 100.0
    }

    private fun owCalc() : Double {
        return (getHeight().toDouble() - 100) * 0.9
    }

    private fun bmpCalc() : Double {
        val bmi = bmiCalc()
        val age = getAge().toDouble()
        return Math.round(((1.39 * bmi) + (0.16 * age) - 19.34) * 100.0) / 100.0
    }

    private fun getName() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val name = sp.getString("NAME", "")
        return name.toString()
    }

    private fun getAge() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val age = sp.getString("AGE", "")
        return age.toString()
    }

    private fun getWeight() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val weight = sp.getString("WEIGHT", "")
        return weight.toString()
    }

    private fun getHeight() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val height = sp.getString("HEIGHT", "")
        return height.toString()
    }


    private fun getPercentage(value: Double, max: Double): Int{
        var x = value / max * 100.0
        return x.toInt()
    }



}