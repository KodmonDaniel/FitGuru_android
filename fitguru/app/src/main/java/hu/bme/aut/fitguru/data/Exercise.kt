package hu.bme.aut.fitguru.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "exercise")
data class Exercise (
    @ColumnInfo(name = "id")@PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "category") var category: Category,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "repeat") var repeat: Int,
    @ColumnInfo(name = "rest") var rest: Int,
    @ColumnInfo(name = "done") var done: Boolean
    ) {
    enum class Category {
        WARMUP, EXERCISE, STRETCH;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): Category? {
                var ret: Category? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(category: Category): Int {
                return category.ordinal
            }
        }
    }
}


