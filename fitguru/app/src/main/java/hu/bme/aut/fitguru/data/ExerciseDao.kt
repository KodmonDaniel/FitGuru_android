package hu.bme.aut.fitguru.data

import androidx.room.*

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM exercise")
    fun getAll(): List<Exercise>

    @Query("SELECT * FROM exercise WHERE done = 1")
    fun getDone(): List<Exercise>

    @Insert
    fun insert(exercise: Exercise): Long

    @Update
    fun update(exercise: Exercise)

    @Delete
    fun deleteItem(exercise: Exercise)
}