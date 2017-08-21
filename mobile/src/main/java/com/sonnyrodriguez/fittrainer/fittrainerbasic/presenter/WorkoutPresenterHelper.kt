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
    var workoutSavePresenter: WorkoutSavePresenter? = null

    fun onCreate(presenter: WorkoutPresenter) {
        workoutPresenter = presenter
        loadWorkouts()
    }

    fun onCreate(savePresenter: WorkoutSavePresenter) {
        workoutSavePresenter = savePresenter
    }

    fun onDestroy() {
        compositeDisposable.dispose()
        workoutPresenter = null
        workoutSavePresenter = null
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

    fun addNewWorkout(workoutObject: WorkoutObject) {
        compositeDisposable.add(Observable.fromCallable { workoutDao.insertWorkout(workoutObject) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    workoutSavePresenter?.workoutSaved()
                })
    }

    fun updateWorkout(workoutObject: WorkoutObject) {
        compositeDisposable.add(Observable.fromCallable { workoutDao.updateWorkout(workoutObject) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    workoutSavePresenter?.workoutSaved()
                })
    }
}
