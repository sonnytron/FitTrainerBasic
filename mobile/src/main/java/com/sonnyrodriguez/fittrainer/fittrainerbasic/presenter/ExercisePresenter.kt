package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject

interface ExercisePresenter {

    fun showTotalExercises(exercises: List<ExerciseObject>)

    fun returnWorkoutExercise(workoutExercises: List<ExerciseObject>)

    fun exerciseAddedTo(position: Int)

    fun scrollTo(position: Int)

    fun returnExerciseFromSearch(exerciseObject: ExerciseObject)
}
