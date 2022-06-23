package hu.bme.aut.fitguru.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Exercise::class], version = 1)
@TypeConverters(value = [Exercise.Category::class])
abstract class TrainingDatabase :RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        fun getDatabase(applicationContext: Context): TrainingDatabase {
            return Room.databaseBuilder(
                applicationContext,
                TrainingDatabase::class.java,
                "training"
            ).build()
        }
    }
}