package hu.bme.aut.fitguru

import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.fitguru.adapter.TrainingAdapter
import hu.bme.aut.fitguru.data.Exercise
import hu.bme.aut.fitguru.data.TrainingDatabase
import hu.bme.aut.fitguru.databinding.ActivityTrainingBinding
import hu.bme.aut.fitguru.fragments.NewExerciseDialogFragment
import kotlin.concurrent.thread


class TrainingActivity : AppCompatActivity(), TrainingAdapter.ExerciseClickListener, NewExerciseDialogFragment.NewExerciseDialogListener{

    private lateinit var binding: ActivityTrainingBinding
    private lateinit var database: TrainingDatabase
    private lateinit var adapter: TrainingAdapter

    private var sum = 0
    private var done = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = TrainingDatabase.getDatabase(applicationContext)

         binding.fab.setOnClickListener{
            NewExerciseDialogFragment().show(
                supportFragmentManager, NewExerciseDialogFragment.TAG
            )
        }

        binding.progressBar.getProgressDrawable().setColorFilter(
            Color.rgb(255,114,0), android.graphics.PorterDuff.Mode.SRC_IN);


        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = TrainingAdapter(this)
        binding.rvTraining.layoutManager = LinearLayoutManager(this)
        binding.rvTraining.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.exerciseDao().getAll()

            runOnUiThread {
                adapter.update(items)
            }
            progress()
        }
    }

    override fun onExerciseDone(item: Exercise) {
        item.done = true
        thread {
            database.exerciseDao().update(item)
            progress()
        }
    }

    override fun onExerciseCreated(newItem: Exercise) {
        thread {
            val insertId = database.exerciseDao().insert(newItem)
            newItem.id = insertId

            runOnUiThread {
                adapter.addItem(newItem)
            }
            progress()
        }
    }

    override fun onExerciseDeleted(item: Exercise) {
        thread {
            database.exerciseDao().deleteItem(item)

            runOnUiThread {
                adapter.delete(item)
            }
            progress()
        }
    }

    private fun progress(){
        done = 0
        sum = 0

        sum = database.exerciseDao().getAll().size
        done = database.exerciseDao().getDone().size

        runOnUiThread {
            binding.progressBar.max = sum
            ObjectAnimator.ofInt(binding.progressBar, "progress", done).start()
        }
     }
}