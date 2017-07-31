package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.arch.persistence.room.Room
import android.content.Context
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseDatabase
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.DataConstants
import dagger.Module
import dagger.Provides

@Module class AppModule(private val context: Context) {
    @Provides fun providesAppContext() = context

    @Provides fun providesExerciseDatabase(context: Context): ExerciseDatabase =
            Room.databaseBuilder(context, ExerciseDatabase::class.java, DataConstants.exerciseDbName).build()

    @Provides fun providesExerciseDao(database: ExerciseDatabase) = database.exerciseDao()
}
