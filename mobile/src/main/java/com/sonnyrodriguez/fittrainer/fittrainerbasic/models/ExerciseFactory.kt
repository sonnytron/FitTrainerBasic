package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject

class ExerciseFactory {
    companion object {
        fun defaultExercises(): List<ExerciseObject> {
            return arrayListOf(
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_one), MuscleEnum.BICEPS.ordinal),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_two), MuscleEnum.CHEST.ordinal),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_three), MuscleEnum.QUADS.ordinal),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_four), MuscleEnum.BICEPS.ordinal),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_five), MuscleEnum.BACK.ordinal),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_six), MuscleEnum.BICEPS.ordinal),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_seven), MuscleEnum.TRICEPS.ordinal),
                    ExerciseObject(FitTrainerApplication.instance.getString(R.string.default_exercise_eight), MuscleEnum.BACK.ordinal)
            )
        }
    }
}
