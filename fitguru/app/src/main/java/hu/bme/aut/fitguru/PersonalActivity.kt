package hu.bme.aut.fitguru


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.fitguru.adapter.PersonalPageAdapter


import hu.bme.aut.fitguru.databinding.ActivityPersonalBinding

class PersonalActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityPersonalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vpProfile.adapter = PersonalPageAdapter(supportFragmentManager)
    }
}
