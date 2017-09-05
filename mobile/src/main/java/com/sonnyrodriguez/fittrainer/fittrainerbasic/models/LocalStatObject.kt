package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutHistoryObject
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

data class LocalStatObject(val title: String,
                           val dateString: String,
                           val totalExercisesString: String,
                           val muscleGroups: String,
                           val durationString: String) {
    companion object {
        fun fromWorkoutHistory(workoutHistoryObject: WorkoutHistoryObject): LocalStatObject {
            val dateOfWorkout = SimpleDateFormat("MM/dd/yyyy", Locale.US).format(Date(workoutHistoryObject.timeEnded))
            val duration = "${TimeUnit.MILLISECONDS.toMinutes(workoutHistoryObject.duration)} minutes, ${TimeUnit.MILLISECONDS.toSeconds(workoutHistoryObject.duration)}"
            var titles = ""
            workoutHistoryObject.names.forEach { titles = "${titles},$it" }
            val total = "${workoutHistoryObject.exercises.count()} exercises"
            var muscles = ""
            workoutHistoryObject.muscles.forEach { muscles = "$muscles,$it" }
            return LocalStatObject(
                    title = titles,
                    dateString = dateOfWorkout,
                    totalExercisesString = total,
                    muscleGroups = muscles,
                    durationString = duration)
        }
    }
}
