package hu.bme.aut.fitguru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.fitguru.adapter.DietPageAdapter
import hu.bme.aut.fitguru.databinding.ActivityDietBinding


class DietActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDietBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDietBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpProfile.adapter = DietPageAdapter(supportFragmentManager)
    }
}