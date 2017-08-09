package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject

interface WorkoutPresenter {

    fun showWorkouts(workouts: List<WorkoutObject>)

    fun workoutAddedTo(position: Int)

    fun scrollTo(position: Int)

    fun workoutUpdatedAt(position: Int)

}

interface WorkoutSavePresenter {
    fun workoutSaved()
}
