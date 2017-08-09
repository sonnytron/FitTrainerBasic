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
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.addFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseFactory
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.WorkoutPresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.WorkoutSavePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.EditWorkoutFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.RequestConstants
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class EditWorkoutFragment: Fragment(), ExercisePresenter, WorkoutSavePresenter {
    lateinit var ui: EditWorkoutFragmentUi
    @Inject lateinit var exerciseHelper: ExercisePresenterHelper
    @Inject lateinit var workoutHelper: WorkoutPresenterHelper
    internal var exerciseList: ArrayList<ExerciseObject> = arrayListOf()
    internal var totalExerciseList: ArrayList<ExerciseObject> = arrayListOf()
    internal var localWorkout: WorkoutObject? = null
    internal val exerciseAdapter = ExerciseAdapter()

    companion object {
        fun newInstance(workoutObject: WorkoutObject?) = EditWorkoutFragment().apply {
            workoutObject?.let {
                localWorkout = it
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = EditWorkoutFragmentUi(exerciseAdapter)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            exerciseHelper.onCreate(this@EditWorkoutFragment)
            workoutHelper.onCreate(this@EditWorkoutFragment)
            exerciseHelper.loadExercises()
            localWorkout?.let {
                ui.workoutTitle = it.title
                getExercises(it)
            }
        }
    }

    internal fun getExercises(workoutObject: WorkoutObject) {
        exerciseHelper.loadExercisesForWorkout(workoutObject)
    }

    override fun showTotalExercises(exercises: List<ExerciseObject>) {
        if (exercises.count() == 0) {
            toast("Exercises List was empty! Creating default exercises.")
            buildDefaultExercises()
        } else {
            totalExerciseList.clear()
            totalExerciseList.addAll(exercises)
        }
    }

    internal fun buildDefaultExercises() {
        ExerciseFactory.defaultExercises().forEach {
            exerciseHelper.addNewExercise(it.title, it.muscleGroupNumber)
        }
    }

    override fun returnWorkoutExercise(workoutExercises: List<ExerciseObject>) {
        exerciseAdapter.changeAll(workoutExercises)
    }

    override fun exerciseAddedTo(position: Int) {

    }

    override fun scrollTo(position: Int) {

    }

    internal fun addNewExercise() {
        addFragment(ExerciseListFragment.newInstance(totalExerciseList), RequestConstants.ADD_EXERCISE_CONSTANT)
    }

    internal fun addExerciseToWorkout(exerciseId: Long) {
        exerciseHelper.findExerciseById(exerciseId)
    }

    override fun returnExerciseFromSearch(exerciseObject: ExerciseObject) {
        exerciseList.add(exerciseObject)
        localWorkout?.let {
            it.exerciseList = exerciseList.map { it.id }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.run {
                when (requestCode) {
                    RequestConstants.ADD_EXERCISE_CONSTANT -> {
                        getLongExtra(KeyConstants.KEY_RESULT_LONG, -1L).let {
                            if (it >= 0L) {
                                addExerciseToWorkout(it)
                            }
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    override fun workoutSaved() {
        targetFragment?.let { targetFrag ->
            val intent = Intent()
            intent.putExtra(KeyConstants.KEY_RESULT_BOOLEAN, true)
            targetFrag.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            fragmentManager.popBackStack()
        }
    }

    internal fun saveWorkoutAndExit() {
        if (localWorkout == null) {
            workoutHelper.addNewWorkout(ui.protectedWorkoutTitle(), exerciseList.map { it.id })
        } else {
            localWorkout?.let {
                it.title = ui.protectedWorkoutTitle()
                workoutHelper.updateWorkout(it)
            }
        }
    }
}
