package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.addFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.ExerciseFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.RequestConstants
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class ExerciseFragment: Fragment(), ExercisePresenter {
    private lateinit var ui: ExerciseFragmentUi

    internal var exerciseAdapter: ExerciseAdapter = ExerciseAdapter()


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
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.run {
                when (requestCode) {
                    RequestConstants.EDIT_EXERCISE_CONSTANT,
                    RequestConstants.NEW_EXERCISE_CONSTANT -> {
                        exerciseHelper.loadExercises()
                    }
                    else -> {

                    }
                }
            }
        }
    }

    override fun onDestroy() {
        exerciseHelper.onDestroy()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun showTotalExercises(exercises: List<ExerciseObject>) {
        exerciseAdapter.changeAll(exercises)
    }

    override fun returnWorkoutExercise(workoutExercises: List<ExerciseObject>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun returnExerciseFromSearch(exerciseObject: ExerciseObject) {
        // We don't need this
    }

    override fun exerciseAddedTo(position: Int) {
        exerciseAdapter.notifyItemInserted(position)
    }

    override fun scrollTo(position: Int) {
        ui.exerciseRecyclerView.smoothScrollToPosition(position)
    }

    internal fun exerciseSelected(exerciseObject: ExerciseObject) {
        addFragment(EditExerciseFragment.newInstance(exerciseObject), RequestConstants.EDIT_EXERCISE_CONSTANT)
    }

    internal fun addNewExercise() {
        addFragment(EditExerciseFragment.newInstance(), RequestConstants.NEW_EXERCISE_CONSTANT)
    }
}
