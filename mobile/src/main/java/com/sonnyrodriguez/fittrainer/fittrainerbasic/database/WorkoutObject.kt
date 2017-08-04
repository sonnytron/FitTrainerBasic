package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation

@Entity(tableName = "workouts")
data class WorkoutObject(
        @PrimaryKey(autoGenerate = true) val id: Long,
        val title: String = ""
)
