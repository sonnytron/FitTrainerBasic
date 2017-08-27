package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

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
        val workoutTitle = arguments.getString(KeyConstants.WORKOUT_TITLE_TEXT)
        ui = StartWorkoutFragmentUi(localWorkouts.first(), localWorkouts.first().title)
        return ui.createView(AnkoContext.Companion.create(ctx, this))
    }

    internal fun forwardAction(localExerciseObject: LocalExerciseObject?) {
        localExerciseObject?.let {
            completedWorkouts.add(it)
        }
        if (currentWorkoutIndex != localWorkouts.count() -1) {
            currentWorkoutIndex++
            localWorkouts[currentWorkoutIndex].let {
                ui.currentExercise = it
                ui.currentExerciseTitle.text = it.title
                ui.currentExerciseCount.text = ui.exerciseCountString()
            }
        } else {
            workoutFinished()
        }
    }

    internal fun backwardAction(localExerciseObject: LocalExerciseObject?) {
        if (currentWorkoutIndex != localWorkouts.count() - 1) {
            currentWorkoutIndex++
            localWorkouts[currentWorkoutIndex].let {
                ui.currentExercise = it
                ui.currentExerciseTitle.text = it.title
                ui.currentExerciseCount.text = ui.exerciseCountString()
            }
        } else {
            workoutFinished()
        }
    }

    internal fun stopAction() {

    }

    internal fun workoutFinished() {

    }
}
