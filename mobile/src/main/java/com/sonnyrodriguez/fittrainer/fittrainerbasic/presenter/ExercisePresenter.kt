package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject

interface ExercisePresenter {

    fun showExercises(exercises: List<ExerciseObject>)

    fun exerciseAddedTo(position: Int)

    fun scrollTo(position: Int)
}
