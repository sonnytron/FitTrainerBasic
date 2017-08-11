package com.sonnyrodriguez.fittrainer.fittrainerbasic.library

import com.sonnyrodriguez.fittrainer.fittrainerbasic.R

enum class NavPosition(val position: Int, val id: Int) {
    EXERCISES(0, R.id.navigation_exercises_item),
    WORKOUTS(1, R.id.navigation_workouts_item),
    STATS(2, R.id.navigation_stats_item);

    companion object {
        fun positionById(id: Int): NavPosition {
            when (id) {
                NavPosition.EXERCISES.id -> return EXERCISES
                NavPosition.WORKOUTS.id -> return WORKOUTS
                NavPosition.STATS.id -> return STATS
                else -> return EXERCISES
            }
        }
    }
}
