package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.WorkoutStatusFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class WorkoutStatusFragment: Fragment() {
    internal lateinit var ui: WorkoutStatusFragmentUi

    internal var localExercises = ArrayList<String>()

    companion object {
        fun newInstance(workoutTitle: String, exerciseTitles: ArrayList<String>) = WorkoutStatusFragment().apply {
            val bundle = Bundle()
            bundle.putStringArrayList(KeyConstants.STRING_ARRAY_EXTRA, exerciseTitles)
            bundle.putString(KeyConstants.WORKOUT_TITLE_TEXT, workoutTitle)
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
        localExercises.clear().apply {
            localExercises.addAll(arguments.getStringArrayList(KeyConstants.STRING_ARRAY_EXTRA))
        }
        ui.muscleGroups.addAll(localExercises)
        val muscleCountText = ctx.getString(R.string.exercise_count_title, localExercises.count())
        val workoutTitle = arguments.getString(KeyConstants.WORKOUT_TITLE_TEXT)
        ui.setMuscleGroupText(title = workoutTitle, muscleCountString = muscleCountText)
    }
}
