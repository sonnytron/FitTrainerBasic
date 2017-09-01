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
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.StartWorkoutFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class StartWorkoutFragment: Fragment() {

    lateinit var ui: StartWorkoutFragmentUi

    var localExercises: ArrayList<String> = arrayListOf()
    var completedWorkouts: ArrayList<LocalExerciseObject> = arrayListOf()
    var localWorkouts: ArrayList<LocalExerciseObject> = arrayListOf()
    lateinit var localWorkoutObject: WorkoutObject
    internal var currentWorkoutIndex = 0
    internal lateinit var appActivity: AppCompatActivity

    companion object {
        fun newInstance(localWorkoutObject: WorkoutObject, appCompatActivity: AppCompatActivity) = StartWorkoutFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(KeyConstants.INTENT_WORKOUT_OBJECT, localWorkoutObject)
            arguments = bundle
            appActivity = appCompatActivity
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        localWorkoutObject = arguments.getParcelable(KeyConstants.INTENT_WORKOUT_OBJECT)
        ui = StartWorkoutFragmentUi(localWorkoutObject.exerciseMetaList.first(), localWorkoutObject.title)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            loadExercises()
        }
    }

    internal fun loadExercises() {
        localExercises.clear().apply {
            localExercises.addAll(localWorkoutObject.exerciseMetaList.map { it.title })
        }
        localWorkouts.addAll(localWorkoutObject.exerciseMetaList)
        val muscleTitles = localWorkoutObject.exerciseMetaList.map { MuscleEnum.fromMuscleNumber(it.muscleGroup).title }
        val muscleArrayList: ArrayList<String> = arrayListOf()
        muscleArrayList.addAll(
                muscleTitles.filter { !muscleArrayList.contains(it) }
        )
        ui.muscleGroups.addAll(muscleArrayList)
        val muscleCountText = ctx.getString(R.string.exercise_count_title, localExercises.count())
        ui.setMuscleGroupText(title = localWorkoutObject.title, muscleCountString = muscleCountText)
    }

    internal fun forwardAction(localExerciseObject: LocalExerciseObject?) {
        localExerciseObject?.let {
            if (it.set == 1L) {
                LocalExerciseObject(it.title,
                        it.count,
                        ui.currentSetCount + 1L,
                        it.exerciseId,
                        it.muscleGroup).apply {
                    completedWorkouts.add(it)
                }
                if (currentWorkoutIndex != localWorkouts.count() -1) {
                    currentWorkoutIndex++
                    localWorkouts[currentWorkoutIndex].let {
                        ui.currentExercise = it
                        ui.updateUi()
                        ui.currentSetCount = 0L
                    }
                } else {
                    workoutFinished()
                }
            } else {
                ui.currentSetCount += 1L
                ui.currentExercise = LocalExerciseObject(title = it.title,
                        count = it.count,
                        exerciseId = it.exerciseId,
                        set = it.set - 1L,
                        muscleGroup = it.muscleGroup)
                ui.updateUi()
            }
        }
    }

    internal fun backwardAction(localExerciseObject: LocalExerciseObject?) {
        localExerciseObject?.let {
            if (it.set == 1L) {
                LocalExerciseObject(it.title,
                        it.count,
                        ui.currentSetCount,
                        it.exerciseId,
                        it.muscleGroup).apply {
                    completedWorkouts.add(it)
                }
                if (currentWorkoutIndex != localWorkouts.count() -1) {
                    currentWorkoutIndex++
                    localWorkouts[currentWorkoutIndex].let {
                        ui.currentExercise = it
                        ui.updateUi()
                    }
                } else {
                    workoutFinished()
                }
            } else {
                ui.currentExercise = LocalExerciseObject(title = it.title,
                        count = it.count,
                        exerciseId = it.exerciseId,
                        set = it.set - 1L,
                        muscleGroup = it.muscleGroup)
                ui.updateUi()
            }
        }
    }

    internal fun stopAction() {
        workoutFinished()
    }

    internal fun workoutFinished() {
        ui.updateStatus(true, completedWorkouts.count())
//
//        targetFragment?.let { targetFrag ->
//            val intent = Intent()
//            intent.putParcelableArrayListExtra(KeyConstants.INTENT_COMPLETED_EXERCISES, completedWorkouts)
//            targetFrag.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
//            fragmentManager.popBackStack()
//        }
    }

    internal fun workoutAction(withFinished: Boolean) {
        if (withFinished) {

        } else {
            ui.currentExercise = localWorkouts.first()
            ui.updateUi()
        }
    }

    /*
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
     */
}
