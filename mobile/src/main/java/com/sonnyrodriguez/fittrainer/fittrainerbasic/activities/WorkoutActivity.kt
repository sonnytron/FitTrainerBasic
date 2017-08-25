package com.sonnyrodriguez.fittrainer.fittrainerbasic.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutStatusFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.replaceFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.replaceFragmentDSL
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.FragmentContainerUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import org.jetbrains.anko.setContentView

class WorkoutActivity: AppCompatActivity() {

    internal var exercises = ArrayList<LocalExerciseObject>()

    var completedWorkout = false
    var workoutTitle = ""

    companion object {
        fun newIntent(exerciseTitle: String, localExercises: ArrayList<LocalExerciseObject>): Intent {
            val intent = Intent(FitTrainerApplication.instance, WorkoutActivity::class.java)
            intent.putParcelableArrayListExtra(KeyConstants.LOCAL_EXERCISE_LIST, localExercises)
            intent.putExtra(KeyConstants.WORKOUT_TITLE_TEXT, exerciseTitle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentContainerUi().setContentView(this)
        exercises.clear()
        val exerciseList = intent.getParcelableArrayListExtra<LocalExerciseObject>(KeyConstants.LOCAL_EXERCISE_LIST)
        exercises.addAll(exerciseList)
        workoutTitle = intent.getStringExtra(KeyConstants.WORKOUT_TITLE_TEXT)
        if (completedWorkout) {
            // show workout finished status
        } else {
            initializeStatus()
        }
    }

    internal fun initializeStatus() {
        val muscleTitles = exercises.map { MuscleEnum.fromMuscleNumber(it.muscleGroup).title }
        val limitedMuscles = ArrayList<String>()
        limitedMuscles.addAll(
                muscleTitles.filter { !limitedMuscles.contains(it) }
        )
        replaceFragmentDSL(WorkoutStatusFragment.newInstance(workoutTitle = workoutTitle,
                exerciseTitles = limitedMuscles))
    }

}
