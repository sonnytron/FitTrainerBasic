package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable

@Dao interface ExerciseDao {

    @Query("select * from exercise")
    fun getAllExercises(): Flowable<List<ExerciseObject>>

    @Query("select * from exercise where id = :id")
    fun findExerciseById(id: Long): ExerciseObject

    @Insert(onConflict = REPLACE)
    fun insertExercise(exerciseObject: ExerciseObject)

    @Update(onConflict = REPLACE)
    fun updateExercise(exerciseObject: ExerciseObject)

    @Delete
    fun deleteExercise(exerciseObject: ExerciseObject)

}
