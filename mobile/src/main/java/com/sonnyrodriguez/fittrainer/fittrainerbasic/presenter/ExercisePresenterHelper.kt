package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseDao
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
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
        loadExercises()
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
                }))
        presenter?.showExercises(exercises)
    }

    fun addNewExercise(exerciseTitle: String, muscleGroupNumber: Int) {
        val newExercise = ExerciseObject(title = exerciseTitle, muscleGroupNumber = muscleGroupNumber)
        compositeDisposable.add(Observable.fromCallable { exerciseDao.insertExercise(newExercise) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
