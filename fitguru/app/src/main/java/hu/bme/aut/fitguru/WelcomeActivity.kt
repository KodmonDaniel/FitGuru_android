package hu.bme.aut.fitguru

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import hu.bme.aut.fitguru.databinding.ActivityWelcomeBinding
import hu.bme.aut.fitguru.fragments.NewNameDialogFragment
import java.util.*

class WelcomeActivity : AppCompatActivity(), NewNameDialogFragment.NewNameDialogListener  {

    private  lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!userExist()){
            NewNameDialogFragment().show(
                supportFragmentManager, NewNameDialogFragment.TAG)
        }
        else{
            val lastTime = "Last time: " + getLastStartTime()
            binding.tvLast.text = lastTime
        }

        val welcome = "Welcome\n" + getName()
        binding.welcome.text = welcome

        binding.btWelcome.setOnClickListener {
            if(userExist()) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
            else {
                NewNameDialogFragment().show(
                    supportFragmentManager, NewNameDialogFragment.TAG)
            }
        }

        saveLastStartTime()
    }

    private fun saveLastStartTime() {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putString("KEY_LAST_START", Date(System.currentTimeMillis()).toString())
        editor.apply()
    }

    private fun getLastStartTime() : String? {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        return sp.getString("KEY_LAST_START", "")
    }

    private fun userExist() : Boolean{
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        return sp.getString("NAME",null) != null
    }

    private fun getName() : String {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val name = sp.getString("NAME", "")
        return name.toString()
    }

    override fun onDialogNewUser(name: String?, age: String?, weight: String?, height: String?) {
        val sp = PreferenceManager.getDefaultSharedPreferences(this)
        val editor: SharedPreferences.Editor = sp.edit()
        editor.putString("NAME",name)
        editor.putString("AGE",age)
        editor.putString("WEIGHT",weight)
        editor.putString("HEIGHT",height)
        editor.putString("MAX_SUGAR","38.0")
        editor.putString("MAX_CAL","2500.0")
        editor.putString("MAX_CARB","275.0")
        editor.putString("MAX_FAT","60.0")
        editor.apply()
    }
}