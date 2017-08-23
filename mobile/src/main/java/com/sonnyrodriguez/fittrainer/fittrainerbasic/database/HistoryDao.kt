package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Update

import io.reactivex.Flowable

@Dao interface HistoryDao {

    @Query("select * from history")
    fun getAllHistory(): Flowable<List<WorkoutHistoryObject>>

    @Insert(onConflict = REPLACE)
    fun insertHistory(historyObject: WorkoutHistoryObject)

    @Update(onConflict = REPLACE)
    fun updateHistory(historyObject: WorkoutHistoryObject)

}
