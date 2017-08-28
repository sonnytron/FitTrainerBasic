package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.addFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.addFragmentDSL
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.replaceFragmentDSL
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.WorkoutStatusFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.RequestConstants
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

class WorkoutStatusFragment: Fragment() {
    internal lateinit var ui: WorkoutStatusFragmentUi

    internal var localExercises = ArrayList<String>()
    internal lateinit var localWorkout: WorkoutObject
    internal var workoutCompleted: Boolean = false
    internal lateinit var appActivity: AppCompatActivity

    companion object {
        fun newInstance(workoutObject: WorkoutObject, appCompatActivity: AppCompatActivity) = WorkoutStatusFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(KeyConstants.INTENT_WORKOUT_OBJECT, workoutObject)
            appActivity = appCompatActivity
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = WorkoutStatusFragmentUi()
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            loadExercises()
        }
    }

    internal fun loadExercises() {
        localWorkout = arguments.getParcelable(KeyConstants.INTENT_WORKOUT_OBJECT)
        localExercises.clear().apply {
            localExercises.addAll(localWorkout.exerciseMetaList.map { it.title })
        }
        val muscleTitles = localWorkout.exerciseMetaList.map { MuscleEnum.fromMuscleNumber(it.muscleGroup).title }
        val muscleArrayList: ArrayList<String> = arrayListOf()
        muscleArrayList.addAll(
                muscleTitles.filter { !muscleArrayList.contains(it) }
        )
        ui.muscleGroups.addAll(muscleArrayList)
        val muscleCountText = ctx.getString(R.string.exercise_count_title, localExercises.count())
        ui.setMuscleGroupText(title = localWorkout.title, muscleCountString = muscleCountText)
    }

    internal fun startWorkout() {
        val workoutArrayList = ArrayList<LocalExerciseObject>()
        workoutArrayList.addAll(localWorkout.exerciseMetaList)
        StartWorkoutFragment.newInstance(workoutArrayList,
                localWorkout.title).apply {
            addFragmentDSL(this, RequestConstants.INTENT_EXERCISE_LIST)
        }
    }

    internal fun endWorkout() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.run {
                when (requestCode) {
                    RequestConstants.INTENT_EXERCISE_LIST -> {
                        val completedExercises: ArrayList<LocalExerciseObject> = getParcelableArrayListExtra(KeyConstants.INTENT_COMPLETED_EXERCISES)
                        toast("You completed ${completedExercises.count()} exercises! Congratulations!")
                    }
                }
            }
        }
    }
}
