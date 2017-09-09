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
        fun durationString(workoutHistoryObject: WorkoutHistoryObject): String {
            return "${TimeUnit.MILLISECONDS.toMinutes(workoutHistoryObject.duration)} minutes, ${TimeUnit.MILLISECONDS.toSeconds(workoutHistoryObject.duration)}"
        }

        fun dateCompleteString(workoutHistoryObject: WorkoutHistoryObject): String {
            val dateDone = Date(workoutHistoryObject.timeStarted)
            return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(dateDone)
        }

        fun totalExercisesDone(workoutHistoryObject: WorkoutHistoryObject): String {
            var titles = ""
            workoutHistoryObject.names.forEach { titles = "${titles},$it" }
            return titles
        }

        fun musclesString(workoutHistoryObject: WorkoutHistoryObject): String {
            var muscles = ""
            workoutHistoryObject.muscles.forEach {
                muscles = "${muscles},${MuscleEnum.fromMuscleNumber(it.toInt()).title}"
            }
            return muscles
        }

        fun totalCount(workoutHistoryObject: WorkoutHistoryObject): String {
            return "${workoutHistoryObject.exercises.count()} exercises"
        }
    }
}
