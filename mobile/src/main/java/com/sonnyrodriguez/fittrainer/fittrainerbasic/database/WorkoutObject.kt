package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*

@Entity(tableName = "workouts")
data class WorkoutObject (@ColumnInfo(name = "workout_title") var title: String,
                          @ColumnInfo(name = "workout_exercise_map") var exerciseMap: LinkedHashMap<Long, Long>) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
