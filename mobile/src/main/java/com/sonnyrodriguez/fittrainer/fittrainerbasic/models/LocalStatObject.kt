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
            val timeDifferenceInMs = Date(workoutHistoryObject.timeEnded).time - Date(workoutHistoryObject.timeStarted).time
            val days = TimeUnit.MILLISECONDS.toDays(timeDifferenceInMs)
            val hours = TimeUnit.MILLISECONDS.toHours(timeDifferenceInMs) - TimeUnit.DAYS.toHours(days)
            val minutes = TimeUnit.MILLISECONDS.toMinutes(timeDifferenceInMs) - TimeUnit.HOURS.toMinutes(hours)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(timeDifferenceInMs) - TimeUnit.MINUTES.toSeconds(minutes)
            if (minutes > 0) {
                return "$minutes minutes, $seconds seconds"
            } else {
                return "$seconds seconds"
            }
        }

        fun dateCompleteString(workoutHistoryObject: WorkoutHistoryObject): String {
            val dateDone = Date(workoutHistoryObject.timeStarted)
            return SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(dateDone)
        }

        fun totalExercisesDone(workoutHistoryObject: WorkoutHistoryObject): String {
            var titles = ""
            workoutHistoryObject.names.forEachIndexed { index, titleString ->
                if (index == 0) {
                    titles = "$titleString"
                } else {
                    titles = "$titles, $titleString"
                }
            }
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
