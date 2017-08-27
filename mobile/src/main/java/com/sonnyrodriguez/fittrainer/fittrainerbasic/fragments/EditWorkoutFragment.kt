package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.activities.WorkoutActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseCountAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.addFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseFactory
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseListObject
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
import org.jetbrains.anko.support.v4.longToast
import javax.inject.Inject

class EditWorkoutFragment: Fragment(), ExercisePresenter, WorkoutSavePresenter {
    lateinit var ui: EditWorkoutFragmentUi
    @Inject lateinit var exerciseHelper: ExercisePresenterHelper
    @Inject lateinit var workoutHelper: WorkoutPresenterHelper
    internal var totalExerciseList: ArrayList<ExerciseObject> = arrayListOf()
    internal var localWorkout: WorkoutObject? = null
    internal val exerciseCountAdapter: ExerciseCountAdapter = ExerciseCountAdapter()
    internal var isEditing = false
    internal var isNewWorkout = true

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
                isNewWorkout = false
                ui.updateUi(it)
                ui.workoutTitle = it.title
                addExercisesToAdapter()
                ui.switchEditMode(false)
            }
        }
    }

    override fun showTotalExercises(exercises: List<ExerciseObject>) {
        if (exercises.count() == 0) {
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
                workoutObject.exerciseMetaList.find { it.exerciseId == exerciseObject.id }.let { matchedObject ->
                    // add to adapter
                    matchedObject?.let {
                        for (i in 1..it.set) {
                            exerciseCountAdapter.add(ExerciseListObject(title = it.title, exerciseCount = it.count, exerciseId = it.exerciseId, muscleGroupNum = it.muscleGroup))
                        }
                        exerciseCountAdapter.notifyItemInserted(it.set.toInt() - 1)
                    }
                }
            }
            exerciseCountAdapter.notifyItemInserted(workoutExercises.count() - 1)
        }
    }

    override fun exerciseAddedTo(position: Int) {

    }

    override fun scrollTo(position: Int) {

    }

    internal fun workoutAction() {
        if (isEditing) {
            addFragment(ExerciseListFragment.newInstance(totalExerciseList), RequestConstants.ADD_EXERCISE_CONSTANT)
        } else {
            startWorkout()
        }
    }

    internal fun startWorkout() {
        localWorkout?.let { workoutObject ->
            val exerciseArrayList = ArrayList<LocalExerciseObject>()
            exerciseArrayList.addAll(workoutObject.exerciseMetaList)
            WorkoutActivity.newIntent(workoutObject).apply {
                startActivity(this)
            }
        }
    }

    override fun returnExerciseFromSearch(exerciseObject: ExerciseObject) {
        // might not need this
    }

    internal fun addExercisesToAdapter() {
        localWorkout?.let { localObject ->
            localObject.exerciseMetaList.forEach { exerciseMeta ->
                for (i in 1..exerciseMeta.set) {
                    exerciseCountAdapter.add(ExerciseListObject(
                            title = exerciseMeta.title,
                            exerciseCount = exerciseMeta.count,
                            exerciseId = exerciseMeta.exerciseId,
                            muscleGroupNum = exerciseMeta.muscleGroup), false)
                }
                exerciseCountAdapter.notifyItemInserted(exerciseMeta.set.toInt() - 1)
            }
        }
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
                        val muscleNumber = getIntExtra(KeyConstants.KEY_RESULT_INT, 0)
                        LocalExerciseObject(exerciseTitle, exerciseCount, setCount, exerciseId, muscleNumber).let {
                            addLocalExercisesToWorkout(it)
                            addLocalExercisesToAdapter(it)
                        }
                    }
                    else -> {

                    }
                }
            }
        }
    }

    internal fun addLocalExercisesToAdapter(newLocalExerciseObject: LocalExerciseObject) {
        for (i in 1..newLocalExerciseObject.set) {
            ExerciseListObject(newLocalExerciseObject.title, newLocalExerciseObject.count, newLocalExerciseObject.exerciseId, newLocalExerciseObject.muscleGroup).let { newListObject ->
                exerciseCountAdapter.add(newListObject, false)
            }
        }
        exerciseCountAdapter.notifyItemInserted(newLocalExerciseObject.set.toInt() - 1)
    }

    internal fun addLocalExercisesToWorkout(newLocalExerciseObject: LocalExerciseObject) {
        if (localWorkout == null) {
            localWorkout = WorkoutObject(ui.protectedWorkoutTitle(), listOf(newLocalExerciseObject))
        } else {
            localWorkout?.let {
                val currentLocal = arrayListOf<LocalExerciseObject>()
                currentLocal.addAll(it.exerciseMetaList)
                currentLocal.add(newLocalExerciseObject)
                it.exerciseMetaList = currentLocal.toList()
            }
        }
    }

    override fun workoutSaved() {
        targetFragment?.let { targetFrag ->
            val intent = Intent()
            intent.putExtra(KeyConstants.KEY_RESULT_BOOLEAN, true)
            targetFrag.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
        }
        ui.switchEditMode(false)
    }

    internal fun saveWorkoutToDatabase() {
        localWorkout?.let { workoutObject ->
            if (isNewWorkout) {
                workoutHelper.addNewWorkout(workoutObject)
            } else {
                workoutHelper.updateWorkout(workoutObject)
            }
        }
    }

    internal fun editOrSaveWorkout() {
        if (isEditing) {
            saveWorkoutToDatabase()
        } else {
            ui.switchEditMode(editEnabled = true)
        }
        isEditing = !isEditing
    }
}
