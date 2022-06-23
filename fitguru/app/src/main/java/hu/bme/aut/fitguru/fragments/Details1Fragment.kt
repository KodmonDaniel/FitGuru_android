package hu.bme.aut.fitguru.fragments


import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.fitguru.databinding.Details1Binding


 class Details1Fragment : Fragment(){
    private lateinit var binding: Details1Binding

    var sugar = 0.0
    var calories = 0.0
    var carbohydrates = 0.0
    var fat = 0.0

    var max_sugar = 0.0
    var max_calories = 0.0
    var max_carbohydrates = 0.0
    var max_fat = 0.0

    var sugarVal = false
    var calVar = false
    var carbVal = false
    var fatVal = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Details1Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProgressBars()

        binding.btClear.setOnClickListener {
            reset()
            setProgressBars()
        }

        binding.tvSugar.setOnClickListener {
            sugarVal = !sugarVal
            setProgressBars()
        }
        binding.tvCal.setOnClickListener {
            calVar = !calVar
            setProgressBars()
        }
        binding.tvCarb.setOnClickListener {
            carbVal = !carbVal
            setProgressBars()
        }
        binding.tvFat.setOnClickListener {
            fatVal = !fatVal
            setProgressBars()
        }
    }

     override fun onResume() {
         super.onResume()
         setProgressBars()
     }

   private fun getData(){
        val sp = PreferenceManager.getDefaultSharedPreferences(context)

        sugar = sp.getString("ACT_SUGAR","0.0")?.toDouble() ?: 0.0
        calories = sp.getString("ACT_CAL", "0.0")?.toDouble() ?: 0.0
        carbohydrates = sp.getString("ACT_CARB", "0.0")?.toDouble() ?: 0.0
        fat = sp.getString("ACT_FAT", "0.0")?.toDouble() ?: 0.0

        max_sugar = sp.getString("MAX_SUGAR", "38.0")?.toDouble() ?: 38.0
        max_calories = sp.getString("MAX_CAL", "2500.0")?.toDouble() ?: 2500.0
        max_carbohydrates = sp.getString("MAX_CARB", "275.0")?.toDouble() ?: 275.0
        max_fat = sp.getString("MAX_FAT", "60.0")?.toDouble() ?: 60.0
    }

    private fun setProgressBars(){
        getData()
        binding.sugarProgress.setProgress(getPercentage(sugar, max_sugar))
        binding.calProgress.setProgress(getPercentage(calories, max_calories))
        binding.carbProgress.setProgress(getPercentage(carbohydrates, max_carbohydrates))
        binding.fatProgress.setProgress(getPercentage(fat, max_fat))

        if(sugarVal)
             binding.tvSugar.text = "${String.format("%.1f", sugar)}g /\n${String.format("%.1f", max_sugar)}g"
        else binding.tvSugar.text = getPercentage(sugar, max_sugar).toString() + "%"

        if(calVar)
             binding.tvCal.text = "${String.format("%.1f", calories)} /\n${String.format("%.1f", max_calories)}"
        else binding.tvCal.text = getPercentage(calories, max_calories).toString() + "%"

        if(carbVal)
             binding.tvCarb.text = "${String.format("%.1f", carbohydrates)}g /\n${String.format("%.1f", max_carbohydrates)}g"
        else binding.tvCarb.text = getPercentage(carbohydrates, max_carbohydrates).toString() + "%"

        if(fatVal)
             binding.tvFat.text = "${String.format("%.1f", fat)}g /\n${String.format("%.1f", max_fat)}g"
        else binding.tvFat.text = getPercentage(fat, max_fat).toString() + "%"



        checkOverLimit()
    }

    private fun getPercentage(value: Double, max: Double): Int{
        var x = value / max * 100.0
        return x.toInt()
    }

     private fun reset (){
         val sp = PreferenceManager.getDefaultSharedPreferences(context)
         val editor: SharedPreferences.Editor = sp.edit()
         editor.putString("ACT_SUGAR", "0.0")
         editor.putString("ACT_CAL", "0.0")
         editor.putString("ACT_CARB", "0.0")
         editor.putString("ACT_FAT", "0.0")
         editor.apply()
     }

     private fun checkOverLimit(){
         if(getPercentage(sugar, max_sugar) > 100){
             binding.tvTextSugar.setTextColor(Color.rgb(255,0,0))
             binding.tvSugar.setTextColor(Color.rgb(255,0,0))
         } else {
             binding.tvTextSugar.setTextColor(Color.rgb(0,0,0))
             binding.tvSugar.setTextColor(Color.rgb(0,0,0))
         }
         if(getPercentage(calories, max_calories) > 100){
             binding.tvTextCal.setTextColor(Color.rgb(255,0,0))
             binding.tvCal.setTextColor(Color.rgb(255,0,0))
         } else {
             binding.tvTextCal.setTextColor(Color.rgb(0,0,0))
             binding.tvCal.setTextColor(Color.rgb(0,0,0))
         }
         if(getPercentage(carbohydrates, max_carbohydrates) > 100){
             binding.tvTextCarb.setTextColor(Color.rgb(255,0,0))
             binding.tvCarb.setTextColor(Color.rgb(255,0,0))
         } else {
             binding.tvTextCarb.setTextColor(Color.rgb(0,0,0))
             binding.tvCarb.setTextColor(Color.rgb(0,0,0))
         }
         if(getPercentage(fat, max_fat) > 100){
             binding.tvTextFat.setTextColor(Color.rgb(255,0,0))
             binding.tvFat.setTextColor(Color.rgb(255,0,0))
         } else {
             binding.tvTextFat.setTextColor(Color.rgb(0,0,0))
             binding.tvFat.setTextColor(Color.rgb(0,0,0))
         }

     }
}