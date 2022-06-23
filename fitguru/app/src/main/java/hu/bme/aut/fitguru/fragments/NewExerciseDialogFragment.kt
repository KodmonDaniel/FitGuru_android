package hu.bme.aut.fitguru.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hu.bme.aut.fitguru.R
import hu.bme.aut.fitguru.data.Exercise
import hu.bme.aut.fitguru.databinding.DialogNewExerciseBinding
import java.lang.RuntimeException

class NewExerciseDialogFragment : DialogFragment() {
    interface NewExerciseDialogListener{
        fun onExerciseCreated(newExercise: Exercise)
    }

    private lateinit var listener: NewExerciseDialogListener
    private lateinit var binding: DialogNewExerciseBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewExerciseDialogListener
            ?: throw RuntimeException("Implement interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewExerciseBinding.inflate(LayoutInflater.from(context))
        binding.spCategory.adapter = ArrayAdapter(
            requireContext(),
            R.layout.support_simple_spinner_dropdown_item,
            resources.getStringArray(R.array.category_items)
        )
        return AlertDialog.Builder(requireContext())
            .setTitle(getString(hu.bme.aut.fitguru.R.string.new_exercise))
            .setView(binding.root)
            .setPositiveButton(getString(R.string.ok)){ dialogInterface, i ->
                if(isValid()){
                    listener.onExerciseCreated(getExercise())
                }
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()
    }

    private fun getExercise(): Exercise {
        return Exercise(
            category = Exercise.Category.getByOrdinal(binding.spCategory.selectedItemPosition)
                ?: Exercise.Category.EXERCISE,
            name = binding.etName.text.toString(),
            repeat = binding.etRepeat.text.toString().toIntOrNull() ?: 1,
            rest = binding.etResting.text.toString().toIntOrNull() ?: 0,
            done = false
        )
    }

    private fun isValid(): Boolean {
        return binding.etName.text.isNotEmpty()
    }

    companion object {
        const val TAG = "NewExerciseDialogFragment"
    }
}