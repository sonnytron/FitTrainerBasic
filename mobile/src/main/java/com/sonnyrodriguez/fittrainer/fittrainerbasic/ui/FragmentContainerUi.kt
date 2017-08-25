package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.app.Activity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout

class FragmentContainerUi: AnkoComponent<Activity> {
    override fun createView(ui: AnkoContext<Activity>) = with(ui) {
        frameLayout {
            id = R.id.fragment_container
        }
    }
}
