package hu.bme.aut.fitguru.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import hu.bme.aut.fitguru.DietActivity
import hu.bme.aut.fitguru.TrainingActivity
import hu.bme.aut.fitguru.databinding.DialogApianswerBinding
import kotlinx.coroutines.newSingleThreadContext


class ApiAnswerFragment(val sugarSum: Double,val  calSum: Double,val carbSum: Double,val fatSum: Double) : DialogFragment() {

    private lateinit var binding: DialogApianswerBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogApianswerBinding.inflate(LayoutInflater.from(context))

        binding.tvSugar.text = "${String.format("%.1f", sugarSum)} g"
        binding.tvCalories.text = calSum.toString()
        binding.tvCarbohydrates.text = "${String.format("%.1f", carbSum)} g"
        binding.tvFat.text = "${String.format("%.1f", fatSum)} g"

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton("Add"){ dialogInterface, i ->
               addFoods()
            }
            .setNegativeButton("Cancel", null)
            .create()
    }


    private fun addFoods(){
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

         val newSugar = sp.getString("ACT_SUGAR", "0.0")?.toDouble()?.plus(sugarSum)
         val newCal = sp.getString("ACT_CAL", "0.0")?.toDouble()?.plus(calSum)
         val newCarb = sp.getString("ACT_CARB", "0.0")?.toDouble()?.plus(carbSum)
         val newFat = sp.getString("ACT_FAT", "0.0")?.toDouble()?.plus(fatSum)

        val editor: SharedPreferences.Editor = sp.edit()
        editor.putString("ACT_SUGAR",newSugar.toString())
        editor.putString("ACT_CAL", newCal.toString())
        editor.putString("ACT_CARB",newCarb.toString())
        editor.putString("ACT_FAT", newFat.toString())

        editor.apply()
    }


    companion object {
        const val TAG = "ApiAnswerFragment"
    }
}