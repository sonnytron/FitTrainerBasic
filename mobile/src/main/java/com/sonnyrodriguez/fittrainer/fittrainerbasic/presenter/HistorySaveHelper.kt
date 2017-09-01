package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.HistoryDao
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutHistoryObject
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HistorySaveHelper @Inject constructor(val historyDao: HistoryDao) {
    val compositeDisposable = CompositeDisposable()
    internal var historyPresenter: HistoryPresenter? = null

    fun onCreate(presenter: HistoryPresenter) {
        historyPresenter = presenter
    }

    fun saveWorkoutHistory(workoutHistoryObject: WorkoutHistoryObject) {
        compositeDisposable.add(Observable.fromCallable { historyDao.insertHistory(workoutHistoryObject) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    historyPresenter?.historySaved()
                })
    }
}

/*
    class HistoryPresenterHelper @Inject constructor(val historyDao: HistoryDao) {

    val compositeDisposable = CompositeDisposable()
    internal var historyPresenter: HistoryPresenter? = null

    fun onCreate(presenter: HistoryPresenter) {
        historyPresenter = presenter
    }

    fun loadAllHistory() {
        compositeDisposable.add(historyDao.getAllHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { historyList ->
                    historyPresenter?.loadAllHistory(historyList)
                })
    }
}
 */
