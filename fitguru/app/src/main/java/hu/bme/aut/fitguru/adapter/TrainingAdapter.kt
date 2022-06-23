package hu.bme.aut.fitguru.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.fitguru.R
import hu.bme.aut.fitguru.data.Exercise
import hu.bme.aut.fitguru.databinding.ExerciseBinding

class TrainingAdapter(private val listener: ExerciseClickListener) : RecyclerView.Adapter<TrainingAdapter.TrainingViewHolder>() {

    private val items = mutableListOf<Exercise>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TrainingViewHolder(
        ExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val exercise = items[position]

        holder.binding.ivType.setImageResource(getImageResource(exercise.category))
        holder.binding.tvType.text = exercise.category.name
        holder.binding.tvName.text = exercise.name
        holder.binding.tvRepeat.text = "Repeat: " + exercise.repeat.toString() + "x"
        holder.binding.tvRest.text = "Resting: " + exercise.rest.toString() + " sec"
        if (exercise.done){holder.binding.layout.setBackgroundResource(R.drawable.green_box) }
        else {holder.binding.layout.setBackgroundResource(R.drawable.yellow_box) }

        if(exercise.done){holder.binding.ibDone.visibility = View.GONE}
             else {holder.binding.ibDone.visibility = View.VISIBLE}

        holder.binding.ibDelete.setOnClickListener(){
            listener.onExerciseDeleted(exercise)
        }

        holder.binding.ibDone.setOnClickListener(){
            notifyDataSetChanged()
            listener.onExerciseDone(exercise)

        }
    }


    fun addItem(item: Exercise) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun update(training: List<Exercise>) {
        items.clear()
        items.addAll(training)
        notifyDataSetChanged()
    }

    fun delete(item: Exercise) {
        items.remove(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size


    @DrawableRes()
    private fun getImageResource(category: Exercise.Category): Int{
        return when (category){
            Exercise.Category.WARMUP -> R.drawable.warmup
            Exercise.Category.EXERCISE -> R.drawable.exercise
            Exercise.Category.STRETCH -> R.drawable.stretch
        }
    }

    inner class TrainingViewHolder(val binding: ExerciseBinding) : RecyclerView.ViewHolder(binding.root)

    interface ExerciseClickListener{
        fun onExerciseDeleted(item: Exercise)
        fun onExerciseDone(item: Exercise)
    }
}