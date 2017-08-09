package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseDao
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ExercisePresenterHelper @Inject constructor(val exerciseDao: ExerciseDao) {
    val compositeDisposable = CompositeDisposable()
    val exercises = ArrayList<ExerciseObject>()

    var presenter: ExercisePresenter? = null

    fun onCreate(exercisePresenter: ExercisePresenter) {
        presenter = exercisePresenter
    }

    fun onDestroy() {
        compositeDisposable.dispose()
        presenter = null
    }

    fun loadExercises() {
        compositeDisposable.add(exerciseDao.getAllExercises()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ exerciseList ->
                    exercises.clear()
                    exercises.addAll(exerciseList)
                    (exercises.size - 1).takeIf { it >= 0 }?.let { exercisePosition ->
                        presenter?.exerciseAddedTo(exercisePosition)
                        presenter?.scrollTo(exercisePosition)
                    }
                    showTotalExercises()
                }))

    }

    fun loadExercisesForWorkout(workoutObject: WorkoutObject) {
        workoutObject.exerciseList.forEach { exerciseId ->
            compositeDisposable.add(Observable.fromCallable { exerciseDao.findExerciseById(exerciseId) }
                    .subscribeOn(Schedulers.io())
                    .subscribe { exerciseObj ->
                        presenter?.returnWorkoutExercise(exerciseObj)
                    })
        }
    }

    fun showTotalExercises() {
        presenter?.showTotalExercises(exercises)
    }

    fun addNewExercise(exerciseTitle: String, muscleGroupNumber: Int) {
        val newExercise = ExerciseObject(title = exerciseTitle, muscleGroupNumber = muscleGroupNumber)
        compositeDisposable.add(Observable.fromCallable { exerciseDao.insertExercise(newExercise) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun findExerciseById(exerciseId: Long) {
        compositeDisposable.add(Observable.fromCallable { exerciseDao.findExerciseById(exerciseId) }
                .subscribeOn(Schedulers.io())
                .subscribe { exerciseObj ->
                    presenter?.returnExerciseFromSearch(exerciseObj)
                })
    }
}
