package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*

@Entity(tableName = "exercise")
data class ExerciseObject(@ColumnInfo(name = "exercise_title") var title: String,
                          @ColumnInfo(name = "exercise_muscle_group") var muscleGroupNumber: Int) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
