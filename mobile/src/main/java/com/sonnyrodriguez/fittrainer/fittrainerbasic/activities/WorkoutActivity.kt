package com.sonnyrodriguez.fittrainer.fittrainerbasic.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StartWorkoutFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutStatusFragment
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
    lateinit var workoutObject: WorkoutObject

    companion object {
        fun newIntent(workoutObject: WorkoutObject): Intent {
            val intent = Intent(FitTrainerApplication.instance, WorkoutActivity::class.java)
            intent.putExtra(KeyConstants.INTENT_WORKOUT_OBJECT, workoutObject)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentContainerUi().setContentView(this)
        workoutObject = intent.getParcelableExtra(KeyConstants.INTENT_WORKOUT_OBJECT)
        exercises.clear()
        exercises.addAll(workoutObject.exerciseMetaList)
        workoutTitle = workoutObject.title
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
        replaceFragmentDSL(StartWorkoutFragment.newInstance(workoutObject, this))
    }
}
