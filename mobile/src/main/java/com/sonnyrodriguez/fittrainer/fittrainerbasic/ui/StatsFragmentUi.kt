package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.view.Gravity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StatsFragment
import org.jetbrains.anko.*

class StatsFragmentUi: AnkoComponent<StatsFragment> {
    override fun createView(ui: AnkoContext<StatsFragment>) = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            textView {
                textResource = R.string.stats_fragment_placeholder
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(16)
                gravity = Gravity.CENTER
            }
        }
    }
}
