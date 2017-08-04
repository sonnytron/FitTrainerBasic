package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleGroup
import java.util.*

@Entity(tableName = "exercise")
data class ExerciseObject(@ColumnInfo(name = "exercise_title") var title: String,
                          @ColumnInfo(name = "exercise_muscle_group") var muscleGroupNumber: Int) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    @ColumnInfo(name = "muscle_group")
    @TypeConverters(MuscleGroup::class)
    var muscleGroup: MuscleGroup = MuscleGroup.fromGroup(muscleGroupNumber)
}