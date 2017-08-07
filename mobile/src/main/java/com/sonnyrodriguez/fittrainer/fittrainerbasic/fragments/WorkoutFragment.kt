package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.WorkoutAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.WorkoutPresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.WorkoutPresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.WorkoutFragmentUi
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class WorkoutFragment: Fragment(), WorkoutPresenter {

    lateinit var ui: WorkoutFragmentUi
    internal var workoutAdapter = WorkoutAdapter()

    @Inject lateinit var workoutHelper: WorkoutPresenterHelper

    companion object {
        fun newInstance() = WorkoutFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = WorkoutFragmentUi(workoutAdapter)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            workoutHelper.onCreate(this@WorkoutFragment)
        }
    }

    override fun onDestroy() {
        workoutHelper.onDestroy()
        super.onDestroy()
    }

    internal fun addNewWorkout() {

    }

    override fun showWorkouts(workouts: List<WorkoutObject>) {
        workoutAdapter.changeAll(workouts)
    }

    override fun scrollTo(position: Int) {
        ui.workoutRecyclerView.smoothScrollToPosition(position)
    }

    override fun workoutAddedTo(position: Int) {
        workoutAdapter.notifyItemInserted(position)
    }

    override fun workoutUpdatedAt(position: Int) {
        workoutAdapter.notifyItemChanged(position)
    }
}
