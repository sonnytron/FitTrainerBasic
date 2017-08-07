package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.WorkoutPresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.WorkoutPresenterHelper
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class WorkoutFragment: Fragment(), WorkoutPresenter {

    @Inject lateinit var workoutHelper: WorkoutPresenterHelper

    companion object {
        fun newInstance() = WorkoutFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun showWorkouts(workouts: List<WorkoutObject>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun scrollTo(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun workoutAddedTo(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun workoutUpdatedAt(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
