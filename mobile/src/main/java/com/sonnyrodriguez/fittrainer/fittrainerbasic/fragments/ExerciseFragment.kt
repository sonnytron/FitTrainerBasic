package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.ExerciseFragmentUi
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class ExerciseFragment: Fragment(), ExercisePresenter {
    private lateinit var ui: ExerciseFragmentUi

    internal var exerciseAdapter: ExerciseAdapter = ExerciseAdapter()
    lateinit var muscleAdapter: ArrayAdapter<MuscleEnum>

    @Inject lateinit var exerciseHelper: ExercisePresenterHelper

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = ExerciseFragmentUi(exerciseAdapter)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            exerciseHelper.onCreate(this@ExerciseFragment)
            exerciseHelper.loadExercises()
            attachMuscleAdapter()
        }
    }

    internal fun attachMuscleAdapter() {
        muscleAdapter = ArrayAdapter(ctx, android.R.layout.simple_list_item_1, MuscleEnum.values()).apply {
            ui.muscleSpinner.adapter = this
        }
    }

    override fun onDestroy() {
        exerciseHelper.onDestroy()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun showExercises(exercises: List<ExerciseObject>) {
        exerciseAdapter.changeAll(exercises)
    }

    override fun exerciseAddedTo(position: Int) {
        exerciseAdapter.notifyItemInserted(position)
    }

    override fun scrollTo(position: Int) {
        ui.exerciseRecyclerView.smoothScrollToPosition(position)
    }

    internal fun addNewExercise(title: String, muscleIndex: Int) {
        exerciseHelper.addNewExercise(title, muscleAdapter.getItem(muscleIndex).ordinal)
    }
}
