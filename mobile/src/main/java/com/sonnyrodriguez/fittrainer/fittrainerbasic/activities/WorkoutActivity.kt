package com.sonnyrodriguez.fittrainer.fittrainerbasic.activities

import android.support.v7.app.AppCompatActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject

class WorkoutActivity: AppCompatActivity() {

    internal var exercises = ArrayList<LocalExerciseObject>()

    companion object {
        fun newInstance(localExercises: List<LocalExerciseObject>) = WorkoutActivity().apply {
            exercises.addAll(localExercises)
        }
    }


}
