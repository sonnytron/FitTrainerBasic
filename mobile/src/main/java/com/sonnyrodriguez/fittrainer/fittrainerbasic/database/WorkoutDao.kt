package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable

@Dao interface WorkoutDao {
    @Query("select * from workouts")
    fun getAllWorkouts(): Flowable<List<WorkoutObject>>

    @Query("select * from workouts where id = :id")
    fun findWorkoutById(id: Long): WorkoutObject

    @Insert(onConflict = REPLACE)
    fun insertWorkout(workoutObject: WorkoutObject)

    @Update(onConflict = REPLACE)
    fun updateWorkout(workoutObject: WorkoutObject)

    @Delete
    fun deleteWorkout(workoutObject: WorkoutObject)
}
