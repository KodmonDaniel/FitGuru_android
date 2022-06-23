package hu.bme.aut.fitguru.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import hu.bme.aut.fitguru.R
import hu.bme.aut.fitguru.databinding.DialogNewNameBinding
import java.lang.RuntimeException

class NewNameDialogFragment: DialogFragment() {
    interface NewNameDialogListener {
        fun onDialogNewUser(name: String?, age: String?, weight: String?, height: String?)
    }

    private lateinit var binding: DialogNewNameBinding

    private lateinit var listener: NewNameDialogListener


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewNameBinding.inflate(LayoutInflater.from(context))


        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
             .setPositiveButton(getString(R.string.done)) { _, _->
                    val name = getName()
                    val age = getAge()
                    val weight = getWeight()
                    val height = getHeight()
                    listener.onDialogNewUser(name, age, weight, height)
            }
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewNameDialogListener
            ?: throw RuntimeException("Implement interface!")

    }

    private fun getName(): String {
        if(binding.etName.text.isEmpty()) {return "User"}
        else return binding.etName.text.toString()
    }

    private fun getAge(): String {
        if(binding.etAge.text.isEmpty()) {return "0"}
        else return binding.etAge.text.toString()
    }

    private fun getWeight(): String {
        if(binding.etWeight.text.isEmpty()) {return "0"}
        else return binding.etWeight.text.toString()
    }

    private fun getHeight(): String {
        if(binding.etHeight.text.isEmpty()) {return "0"}
        else return binding.etHeight.text.toString()
    }


    companion object {
        const val TAG = "NewNameDialogFragment"
    }
}
