package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*

class ExerciseActivityUi: AnkoComponent<ExerciseActivity> {

    override fun createView(ui: AnkoContext<ExerciseActivity>) = with(ui) {
        frameLayout {
            id = R.id.container
        }
    }
}
