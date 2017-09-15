package com.sonnyrodriguez.fittrainer.fittrainerbasic

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StatsFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.NavPosition
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.active
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.disableShiftMode
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.replaceFragment
import org.jetbrains.anko.toast

class ExerciseActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val positionKey = "positionKey"

    var navigationPosition: NavPosition = NavPosition.EXERCISES

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        restoreSavedInstanceState(savedInstanceState)
        initializeViews()
        restoreFragment(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(positionKey, navigationPosition.id)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationPosition = NavPosition.positionById(item.itemId)
        return when(item.itemId) {
            R.id.navigation_exercises_item -> loadExerciseFragment()
            R.id.navigation_workouts_item -> loadWorkoutFragment()
            R.id.navigation_stats_item -> loadStatsFragment()
            else -> return false
        }
    }

    internal fun initializeViews() {
        findViewById<BottomNavigationView>(R.id.navigation_menu).apply { initializeBottomNavigation(this) }
    }

    internal fun initializeBottomNavigation(bottomNavigationView: BottomNavigationView) {
        bottomNavigationView.disableShiftMode()
        bottomNavigationView.active(navigationPosition.position)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    fun loadWorkoutFragment(): Boolean {
        replaceFragment(WorkoutFragment.newInstance())
        return true
    }

    fun loadExerciseFragment(): Boolean {
        replaceFragment(ExerciseFragment.newInstance())
        return true
    }

    fun loadStatsFragment(): Boolean {
        replaceFragment(StatsFragment.newInstance())
        return true
    }

    internal fun restoreSavedInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            val id = it.getInt(positionKey, NavPosition.EXERCISES.id)
            navigationPosition = NavPosition.positionById(id)
        }
    }

    internal fun restoreFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            loadExerciseFragment()
        }
    }
}
