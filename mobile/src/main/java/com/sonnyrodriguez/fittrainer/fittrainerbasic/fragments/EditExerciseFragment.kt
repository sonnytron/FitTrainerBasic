package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.EditExerciseFragmentUi
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class EditExerciseFragment: Fragment() {

    lateinit var ui: EditExerciseFragmentUi
    lateinit var muscleAdapter: ArrayAdapter<MuscleEnum>
    internal var internalExerciseObject: ExerciseObject? = null

    companion object {
        fun newInstance(exerciseObject: ExerciseObject? = null) = EditExerciseFragment().apply {
            exerciseObject?.let {
                internalExerciseObject = it
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = EditExerciseFragmentUi()
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            attachMuscleAdapter()
        }
    }

    internal fun attachMuscleAdapter() {
        muscleAdapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, MuscleEnum.values()).apply {
            ui.muscleSpinner.adapter = this
        }
    }
}
