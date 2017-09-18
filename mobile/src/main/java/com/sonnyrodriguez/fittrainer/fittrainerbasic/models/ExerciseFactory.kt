package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject

class ExerciseFactory {
    companion object {
        fun defaultExercises(): List<ExerciseObject> {
            return arrayListOf(
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_one), MuscleEnum.BICEPS.ordinal, arrayListOf()),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_two), MuscleEnum.CHEST.ordinal, arrayListOf()),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_three), MuscleEnum.QUADS.ordinal, arrayListOf()),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_four), MuscleEnum.BICEPS.ordinal, arrayListOf()),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_five), MuscleEnum.BACK.ordinal, arrayListOf()),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_six), MuscleEnum.BICEPS.ordinal, arrayListOf()),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_seven), MuscleEnum.TRICEPS.ordinal, arrayListOf()),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_eight), MuscleEnum.BACK.ordinal, arrayListOf())
            )
        }

        fun exerciseCountFromWorkout(workoutObject: WorkoutObject): Int {
            var initialCount = 0
            workoutObject.exerciseMetaList.forEach {
                initialCount += it.set.toInt()
            }
            return initialCount
        }
    }
}
