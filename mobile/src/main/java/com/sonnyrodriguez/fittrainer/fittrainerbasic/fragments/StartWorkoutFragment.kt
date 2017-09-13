package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutHistoryObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.HistoryPresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.HistorySaveHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.StartWorkoutFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class StartWorkoutFragment: Fragment(), HistoryPresenter {

    lateinit var ui: StartWorkoutFragmentUi

    var localExercises: ArrayList<String> = arrayListOf()
    var completedWorkouts: ArrayList<LocalExerciseObject> = arrayListOf()
    var localWorkouts: ArrayList<LocalExerciseObject> = arrayListOf()
    var muscles: ArrayList<Long> = arrayListOf()
    lateinit var localWorkoutObject: WorkoutObject
    internal var currentWorkoutIndex = 0
    internal lateinit var appActivity: AppCompatActivity

    var startTime: Long = 0L
    var endTime: Long = 0L

    @Inject lateinit var historySaveHelper: HistorySaveHelper

    companion object {
        fun newInstance(localWorkoutObject: WorkoutObject, appCompatActivity: AppCompatActivity) = StartWorkoutFragment().apply {
            val bundle = Bundle()
            bundle.putParcelable(KeyConstants.INTENT_WORKOUT_OBJECT, localWorkoutObject)
            arguments = bundle
            appActivity = appCompatActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        historySaveHelper.onCreate(this)
        localWorkoutObject = arguments.getParcelable(KeyConstants.INTENT_WORKOUT_OBJECT)
        ui = StartWorkoutFragmentUi(localWorkoutObject.exerciseMetaList.first(), localWorkoutObject.title)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            loadExercises()
        }
    }

    internal fun loadExercises() {
        localExercises.clear().apply {
            localExercises.addAll(localWorkoutObject.exerciseMetaList.map { it.title })
        }
        localWorkouts.addAll(localWorkoutObject.exerciseMetaList)
        val muscleTitles = localWorkoutObject.exerciseMetaList.map { MuscleEnum.fromMuscleNumber(it.muscleGroup).title }
        val muscleArrayList: ArrayList<String> = arrayListOf()
        muscleArrayList.addAll(
                muscleTitles.filter { !muscleArrayList.contains(it) }
        )
        ui.muscleGroups.addAll(muscleArrayList)
        val muscleCountText = ctx.getString(R.string.exercise_count_title, localExercises.count())
        ui.setMuscleGroupText(title = localWorkoutObject.title, muscleCountString = muscleCountText)
    }

    internal fun forwardAction(localExerciseObject: LocalExerciseObject?) {
        localExerciseObject?.let {
            if (it.set == 1L) {
                LocalExerciseObject(it.title,
                        it.count,
                        ui.currentSetCount + 1L,
                        it.exerciseId,
                        it.muscleGroup).apply {
                    completedWorkouts.add(it)
                }
                if (currentWorkoutIndex != localWorkouts.count() -1) {
                    currentWorkoutIndex++
                    localWorkouts[currentWorkoutIndex].let {
                        ui.currentExercise = it
                        ui.updateUi()
                        ui.currentSetCount = 0L
                    }
                } else {
                    workoutFinished()
                }
            } else {
                ui.currentSetCount += 1L
                ui.currentExercise = LocalExerciseObject(title = it.title,
                        count = it.count,
                        exerciseId = it.exerciseId,
                        set = it.set - 1L,
                        muscleGroup = it.muscleGroup)
                ui.updateUi()
            }
        }
    }

    internal fun backwardAction(localExerciseObject: LocalExerciseObject?) {
        localExerciseObject?.let {
            if (it.set == 1L) {
                LocalExerciseObject(it.title,
                        it.count,
                        ui.currentSetCount,
                        it.exerciseId,
                        it.muscleGroup).apply {
                    completedWorkouts.add(it)
                }
                if (currentWorkoutIndex != localWorkouts.count() -1) {
                    currentWorkoutIndex++
                    localWorkouts[currentWorkoutIndex].let {
                        ui.currentExercise = it
                        ui.updateUi()
                    }
                } else {
                    workoutFinished()
                }
            } else {
                ui.currentExercise = LocalExerciseObject(title = it.title,
                        count = it.count,
                        exerciseId = it.exerciseId,
                        set = it.set - 1L,
                        muscleGroup = it.muscleGroup)
                ui.updateUi()
            }
        }
    }

    internal fun stopAction() {
        workoutFinished()
    }

    internal fun workoutFinished() {
        endTime = Date().time
        ui.updateStatus(true, completedWorkouts.count())
    }

    internal fun workoutAction(withFinished: Boolean) {
        if (withFinished) {
            saveWorkoutHistory()
        } else {
            startTime = Date().time
            ui.currentExercise = localWorkouts.first()
            ui.updateUi()
        }
    }

    internal fun saveWorkoutHistory() {
        val muscleGroups: ArrayList<Long> = arrayListOf()
        val titleStrings: ArrayList<String> = arrayListOf()
        val exerciseIds: ArrayList<Long> = arrayListOf()
        completedWorkouts.forEach {
            muscleGroups.add(it.muscleGroup.toLong())
            titleStrings.add(it.title)
            exerciseIds.add(it.exerciseId)
        }
        WorkoutHistoryObject(exerciseIds, titleStrings, muscleGroups, startTime, endTime, Date(endTime).time - Date(startTime).time).let {
            historySaveHelper.saveWorkoutHistory(it)
        }
    }

    override fun historySaved() {
        appActivity.finish()
    }

    override fun loadAllHistory(historyObjects: List<WorkoutHistoryObject>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
