package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject

@Entity(tableName = "workouts")
data class WorkoutObject (@ColumnInfo(name = "workout_title") var title: String,
                          @ColumnInfo(name = "exercise_meta_list") var exerciseMetaList: List<LocalExerciseObject>) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
