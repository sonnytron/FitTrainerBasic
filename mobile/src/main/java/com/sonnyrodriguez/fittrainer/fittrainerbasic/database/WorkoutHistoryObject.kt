package com.sonnyrodriguez.fittrainer.fittrainerbasic.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "history")
data class WorkoutHistoryObject(@ColumnInfo(name = "exercise_ids") var exercises: List<Long>,
                                @ColumnInfo(name = "exercise_names") var names: List<String>,
                                @ColumnInfo(name = "muscle_groups") var muscles: List<Long>,
                                @ColumnInfo(name = "time_started") var timeStarted: Long,
                                @ColumnInfo(name = "time_ended") var timeEnded: Long,
                                @ColumnInfo(name = "duration") var duration: Long) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}
