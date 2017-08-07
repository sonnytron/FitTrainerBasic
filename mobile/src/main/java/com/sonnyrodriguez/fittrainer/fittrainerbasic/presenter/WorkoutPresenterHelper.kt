package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutDao
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkoutPresenterHelper @Inject constructor(val workoutDao: WorkoutDao) {
    val compositeDisposable = CompositeDisposable()
    var workoutList: ArrayList<WorkoutObject> = arrayListOf()

    var workoutPresenter: WorkoutPresenter? = null

    fun onCreate(presenter: WorkoutPresenter) {
        workoutPresenter = presenter
        loadWorkouts()
    }

    fun onDestroy() {
        compositeDisposable.dispose()
        workoutPresenter = null
    }

    fun loadWorkouts() {
        compositeDisposable.add(workoutDao.getAllWorkouts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { workouts ->
                    workoutList.clear()
                    workoutList.addAll(workouts)
                    (workoutList.size - 1).takeIf { it >= 0 }?.let {
                        workoutPresenter?.workoutAddedTo(it)
                        workoutPresenter?.scrollTo(it)
                    }
                    workoutPresenter?.showWorkouts(workoutList)
                })
    }

    fun addNewWorkout(title: String, exerciseLongList: List<Long>) {
        val newWorkout = WorkoutObject(title, exerciseLongList)
        compositeDisposable.add(Observable.fromCallable { workoutDao.insertWorkout(newWorkout) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }

    fun updateWorkout(workoutObject: WorkoutObject) {
        compositeDisposable.add(Observable.fromCallable { workoutDao.updateWorkout(workoutObject) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
    }
}
