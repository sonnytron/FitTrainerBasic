package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.WorkoutPresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.EditWorkoutFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.RequestConstants
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class EditWorkoutFragment: Fragment(), ExercisePresenter {
    lateinit var ui: EditWorkoutFragmentUi
    @Inject lateinit var exerciseHelper: ExercisePresenterHelper
    @Inject lateinit var workoutHelper: WorkoutPresenterHelper
    internal var exerciseList: ArrayList<ExerciseObject> = arrayListOf()

    companion object {
        fun newInstance(workoutObject: WorkoutObject?) = EditWorkoutFragment().apply {
            workoutObject?.let {
                getExercises(it)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = EditWorkoutFragmentUi()
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            exerciseHelper.onCreate(this@EditWorkoutFragment)
        }
    }

    internal fun getExercises(workoutObject: WorkoutObject) {
        exerciseHelper.loadExercisesForWorkout(workoutObject)
    }

    override fun showExercises(exercises: List<ExerciseObject>) {
        exerciseList.clear()
        exerciseList.addAll(exercises)
    }

    override fun exerciseAddedTo(position: Int) {

    }

    override fun scrollTo(position: Int) {

    }

    internal fun addExerciseToWorkout(exerciseId: Long) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.run {
                when (requestCode) {
                    RequestConstants.ADD_EXERCISE_CONSTANT -> {
                        getLongExtra(KeyConstants.KEY_RESULT_LONG, -1L).let {
                            if (it >= 0L) {
                                // add new exercise to Workout and update Workout Exercise List
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    /*
        targetFragment?.let { targetFragment ->
            val intent = Intent()
            intent.putExtra(UiConstants.KEY_RESULT_LANGUAGE, language)
            targetFragment.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        } ?: Timber.w("If you want to retrieve data, you should set targetFragment.")
     */
}
