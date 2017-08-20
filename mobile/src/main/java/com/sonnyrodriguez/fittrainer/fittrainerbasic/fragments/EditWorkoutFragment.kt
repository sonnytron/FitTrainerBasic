package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseCountAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.addFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseFactory
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseListObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseSet
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject
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
    internal var totalExerciseList: ArrayList<ExerciseObject> = arrayListOf()
    internal var localWorkout: WorkoutObject? = null
    internal val exerciseCountAdapter: ExerciseCountAdapter = ExerciseCountAdapter()
    internal var pendingLocalExercise: LocalExerciseObject? = null
    internal var pendingExerciseSet: ExerciseSet? = null

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
        ui = EditWorkoutFragmentUi(exerciseCountAdapter)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            exerciseHelper.onCreate(this@EditWorkoutFragment)
            workoutHelper.onCreate(this@EditWorkoutFragment)
            exerciseHelper.loadExercises()
            localWorkout?.let {
                ui.updateUi(it)
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
        localWorkout?.let { workoutObject ->
            workoutExercises.forEach { exerciseObject ->
                workoutObject.exerciseMap.entries.find { it.key == exerciseObject.id }.let { entry ->
                    exerciseCountAdapter.add(ExerciseListObject(exerciseObject, entry?.value ?: 1), false)
                }
            }
            exerciseCountAdapter.notifyItemInserted(workoutExercises.count() - 1)
        }
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
        pendingLocalExercise?.let { localExerciseObject ->
            if (localExerciseObject.exerciseId == exerciseObject.id) {
                exerciseCountAdapter.add(ExerciseListObject(exerciseObject, localExerciseObject.count))
            }
        }
        pendingLocalExercise = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            data?.run {
                when (requestCode) {
                    RequestConstants.ADD_EXERCISE_CONSTANT -> {
                        val exerciseId = getLongExtra(KeyConstants.KEY_RESULT_KEY_LONG, -1L)
                        val exerciseCount = getLongExtra(KeyConstants.KEY_RESULT_LONG, 8L)
                        val setCount = getLongExtra(KeyConstants.KEY_RESULT_SET, 1L)
                        val exerciseTitle = getStringExtra(KeyConstants.KEY_RESULT_TEXT)
                        if (setCount == 1L) {
                            LocalExerciseObject(exerciseTitle, exerciseCount, exerciseId).let {
                                pendingLocalExercise = it
                                addExerciseToWorkout(it.exerciseId)
                            }
                        } else {
                            addSetToWorkout(ExerciseSet(
                                    LocalExerciseObject(exerciseTitle, exerciseCount, exerciseId),
                                    setCount
                            ))
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    internal fun addSetToWorkout(exerciseSet: ExerciseSet) {
        for (i in 0 until exerciseSet.setCount) {
            LocalExerciseObject(exerciseSet.localExerciseObject.title,
                    exerciseSet.localExerciseObject.count,
                    exerciseSet.localExerciseObject.exerciseId).let {
                pendingLocalExercise = it
                addExerciseToWorkout(it.exerciseId)
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
            val newWorkoutMap = LinkedHashMap<Long, Long>()
            exerciseCountAdapter.internalItemList.forEach { exerciseListObject ->
                newWorkoutMap[exerciseListObject.exerciseObject.id] = exerciseListObject.exerciseCount
            }
            workoutHelper.addNewWorkout(ui.protectedWorkoutTitle(), newWorkoutMap)
        } else {
            localWorkout?.let {
                it.title = ui.protectedWorkoutTitle()
                exerciseCountAdapter.internalItemList.forEach { exerciseListObject ->
                    it.exerciseMap[exerciseListObject.exerciseObject.id] = exerciseListObject.exerciseCount
                }
                workoutHelper.updateWorkout(it)
            }
        }
    }
}
