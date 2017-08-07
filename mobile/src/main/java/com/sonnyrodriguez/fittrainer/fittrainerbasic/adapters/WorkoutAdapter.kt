package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.GymAdapter
import org.jetbrains.anko.AnkoContext

class WorkoutAdapter: GymAdapter<WorkoutObject>() {
    override fun onCreateInternalItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WorkoutViewHolder(WorkoutItemUi().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun onBindInternalItemView(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is WorkoutViewHolder) {
            viewHolder.bind(getInternalItem(position))
        }
    }
}
