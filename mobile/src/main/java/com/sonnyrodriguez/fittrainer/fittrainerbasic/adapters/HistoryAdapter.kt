package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutHistoryObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.GymAdapter
import org.jetbrains.anko.AnkoContext

class HistoryAdapter: GymAdapter<WorkoutHistoryObject>() {

    override fun onCreateInternalItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryItemViewHolder(HistoryItemUi().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun onBindInternalItemView(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is HistoryItemViewHolder) {
            viewHolder.bind(getInternalItem(position))
        }
    }
}
