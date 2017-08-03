package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.ExerciseFragmentUi
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class ExerciseFragment: Fragment(), ExercisePresenter {
    private lateinit var ui: ExerciseFragmentUi

    internal val exerciseAdapter: ExerciseAdapter = ExerciseAdapter()

    @Inject lateinit var exerciseHelper: ExercisePresenterHelper

    companion object {
        fun newInstance() = ExerciseFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        ui = ExerciseFragmentUi(exerciseAdapter)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            exerciseHelper.onCreate(this@ExerciseFragment)
        }
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

    internal fun addNewExercise(title: String) {
        exerciseHelper.addNewExercise(title, MuscleGroup.CHEST.ordinal)
    }
}
