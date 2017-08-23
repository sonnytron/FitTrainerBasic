package com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutHistoryObject

interface HistoryPresenter {
    fun loadAllHistory(historyObjects: List<WorkoutHistoryObject>)
}
