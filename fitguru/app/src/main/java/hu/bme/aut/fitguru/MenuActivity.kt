package hu.bme.aut.fitguru

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.fitguru.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btPersonal.setOnClickListener{
            val intent = Intent(this,PersonalActivity::class.java)
            startActivity(intent)
        }
        binding.btTraining.setOnClickListener{
            val intent = Intent(this,TrainingActivity::class.java)
            startActivity(intent)
        }


        binding.btFood.setOnClickListener{
            val intent = Intent(this, DietActivity::class.java)
            startActivity(intent)
        }



        binding.btTips.setOnClickListener{
            val intent = Intent(this,HealthActivity::class.java)
            startActivity(intent)
        }
    }


















}