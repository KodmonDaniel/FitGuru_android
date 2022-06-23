package hu.bme.aut.fitguru.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class FoodAPI {

    companion object {
        private const val BASE_URL = "https://api.calorieninjas.com/v1/nutrition?query="
        private const val RESPONSE_ERROR = "ERROR"
        private const val API_KEY = "KcDJM3hjoo4sbwQFS78cMQ==rezUFHpe2XnJmgNh"
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private fun httpGet(url: String): String {
        val request = Request.Builder()
            .url(url)
            .header("X-Api-Key", API_KEY)
            .build()

        val response = client.newCall(request).execute()
        return response.body?.string() ?: "EMPTY"
    }

    fun getFoodDetails(food: String): String {
        return try {
             val queryUrl = "$BASE_URL$food"
            Log.d("apia",food)
             httpGet(queryUrl)
        } catch (e: Exception) {
            e.printStackTrace()
            RESPONSE_ERROR
        }
    }

}