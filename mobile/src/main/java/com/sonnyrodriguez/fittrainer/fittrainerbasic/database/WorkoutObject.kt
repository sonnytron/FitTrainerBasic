package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

@Entity(tableName = "workouts")
data class WorkoutObject (@ColumnInfo(name = "workout_title") var title: String,
                          @ColumnInfo(name = "workout_exercise_list") var exerciseList: List<Long>) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
