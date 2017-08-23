package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.Converters

@Database(entities = arrayOf(ExerciseObject::class, WorkoutObject::class, WorkoutHistoryObject::class), version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun historyDao(): HistoryDao
}
