package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.StartWorkoutFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class StartWorkoutFragment: Fragment() {

    lateinit var ui: StartWorkoutFragmentUi

    var localWorkouts: ArrayList<LocalExerciseObject> = arrayListOf()
    var completedWorkouts: ArrayList<LocalExerciseObject> = arrayListOf()
    internal var currentWorkoutIndex = 0

    companion object {
        fun newInstance(workoutExercises: ArrayList<LocalExerciseObject>,
                        workoutTitle: String) = StartWorkoutFragment().apply {
            val bundle = Bundle()
            bundle.putParcelableArrayList(KeyConstants.LOCAL_EXERCISE_LIST, workoutExercises)
            bundle.putString(KeyConstants.WORKOUT_TITLE_TEXT, workoutTitle)
            arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        localWorkouts = arguments.getParcelableArrayList(KeyConstants.LOCAL_EXERCISE_LIST)
        ui = StartWorkoutFragmentUi(localWorkouts.first(), localWorkouts.first().title)
        return ui.createView(AnkoContext.Companion.create(ctx, this))
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
        targetFragment?.let { targetFrag ->
            val intent = Intent()
            intent.putParcelableArrayListExtra(KeyConstants.INTENT_COMPLETED_EXERCISES, completedWorkouts)
            targetFrag.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            fragmentManager.popBackStack()
        }
    }
}
