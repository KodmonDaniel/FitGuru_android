package hu.bme.aut.fitguru.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import hu.bme.aut.fitguru.databinding.Details2Binding
import hu.bme.aut.fitguru.network.FoodAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.math.roundToInt


class Details2Fragment : Fragment(){
    private lateinit var binding: Details2Binding
    private lateinit var foodAPI: FoodAPI


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = Details2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodAPI = FoodAPI()


        binding.btOk.setOnClickListener {
            if(binding.etFood.text.toString() == ""){
                Toast.makeText(context, "EMPTY FIELD!", Toast.LENGTH_SHORT).show()
            }
            else {
                apiCall()
            }
        }
    }

    private fun apiCall(){
        val food = binding.etFood.text.toString()
        coroutine {
            foodAPI.getFoodDetails(food)
        }
    }

    private fun coroutine(call: () -> String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val response : String
            response = call()
            Log.d("apia",response)
            withContext(Dispatchers.Main) { showResponse(response) }
        }
    }

    private fun showResponse(response: String) {
            if (response == "ERROR") {
                Log.d("apia", "no api answer")
                return
            }

            val jObject = JSONObject(response)

            val arr: JSONArray = jObject.getJSONArray("items")

            var sugarSum = 0.0
            var calSum = 0.0
            var carbSum = 0.0
            var fatSum = 0.0

            for (i in 0 until arr.length()) {
                sugarSum += arr.getJSONObject(i).getString("sugar_g").toString().toDouble()
                calSum += arr.getJSONObject(i).getString("calories").toString().toDouble()
                carbSum += arr.getJSONObject(i).getString("carbohydrates_total_g").toString()
                    .toDouble()
                fatSum += arr.getJSONObject(i).getString("fat_total_g").toString().toDouble()
            }


            ApiAnswerFragment(sugarSum, calSum, carbSum, fatSum)
                .show(childFragmentManager, ApiAnswerFragment.TAG)

    }

}