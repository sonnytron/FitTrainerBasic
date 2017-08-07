package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.view.View
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.WorkoutAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout

class WorkoutFragmentUi(val workoutListAdapter: WorkoutAdapter): AnkoComponent<WorkoutFragment> {
    override fun createView(ui: AnkoContext<WorkoutFragment>) = with(ui) {
        frameLayout {

        }
    }
}
