package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.view.View
import android.widget.Spinner
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditExerciseFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout

class EditExerciseFragmentUi: AnkoComponent<EditExerciseFragment> {

    lateinit var muscleSpinner: Spinner

    override fun createView(ui: AnkoContext<EditExerciseFragment>) = with(ui) {
        relativeLayout {

        }
    }
}
