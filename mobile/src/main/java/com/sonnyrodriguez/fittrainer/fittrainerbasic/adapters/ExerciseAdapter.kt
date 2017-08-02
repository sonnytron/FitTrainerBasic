package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.GymAdapter
import org.jetbrains.anko.AnkoContext

class ExerciseAdapter: GymAdapter<ExerciseObject>() {
    override fun onCreateInternalItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExerciseViewHolder(ExerciseItemUi().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindInternalItemView(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ExerciseViewHolder) {
            viewHolder.bind(getInternalItem(position))
        }
    }
}