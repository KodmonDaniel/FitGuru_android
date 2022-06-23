package hu.bme.aut.fitguru.fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.fitguru.databinding.ProfileInfoBinding
import hu.bme.aut.fitguru.databinding.ProfileSettingsBinding

class ProfileSettingsFragment : Fragment() {

    private lateinit var binding: ProfileSettingsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ProfileSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            recalculate()
        }
    }

    override fun onResume() {
        super.onResume()

        setData()
    }

    private fun recalculate() {
        var name = binding.tvName.text.toString()
        var age = binding.etAge.text.toString()
        var weight = binding.etWeight.text.toString()
        var height = binding.etHeight.text.toString()
        var max_sugar = binding.etMaxSugar.text.toString()
        var max_cal = binding.etMaxCal.text.toString()
        var max_carb = binding.etMaxCarbo.text.toString()
        var max_fat = binding.etMaxFat.text.toString()

        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putString("NAME",name)
        editor.putString("AGE",age)
        editor.putString("WEIGHT",weight)
        editor.putString("HEIGHT",height)
        editor.putString("MAX_SUGAR",max_sugar)
        editor.putString("MAX_CAL",max_cal)
        editor.putString("MAX_CARB",max_carb)
        editor.putString("MAX_FAT",max_fat)
        editor.apply()

        setData()
    }

    private fun setData(){
        binding.tvName.text = getName()
        binding.etWeight.setText(getWeight())
        binding.etAge.setText(getAge())
        binding.etHeight.setText(getHeight())

        binding.etMaxSugar.setText(getMSugar())
        binding.etMaxCal.setText(getMCal())
        binding.etMaxCarbo.setText(getMCarb())
        binding.etMaxFat.setText(getMFat())
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

    private fun getMSugar() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val name = sp.getString("MAX_SUGAR", "")
        return name.toString()
    }

    private fun getMCal() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val age = sp.getString("MAX_CAL", "")
        return age.toString()
    }

    private fun getMCarb() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val weight = sp.getString("MAX_CARB", "")
        return weight.toString()
    }

    private fun getMFat() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val height = sp.getString("MAX_FAT", "")
        return height.toString()
    }
}