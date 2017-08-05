package com.sonnyrodriguez.fittrainer.fittrainerbasic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.ExerciseActivityUi
import org.jetbrains.anko.setContentView

class ExerciseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExerciseActivityUi().setContentView(this)
        val exerciseFragment = ExerciseFragment.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, exerciseFragment)
        }.commit()
    }
}
