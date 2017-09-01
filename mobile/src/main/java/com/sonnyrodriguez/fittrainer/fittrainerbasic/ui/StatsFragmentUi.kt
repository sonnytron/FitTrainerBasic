package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v7.widget.LinearLayoutManager
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.HistoryAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StatsFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class StatsFragmentUi(var historyAdapter: HistoryAdapter): AnkoComponent<StatsFragment> {
    override fun createView(ui: AnkoContext<StatsFragment>) = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            recyclerView {
                clipToPadding = false
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = this@StatsFragmentUi.historyAdapter.apply {

                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}
