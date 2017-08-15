package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.library.GymAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseListObject
import org.jetbrains.anko.AnkoContext

class ExerciseCountAdapter: GymAdapter<ExerciseListObject>() {
    override fun onCreateInternalItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ExerciseCountViewHolder(ExerciseCountItemUi().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun onBindInternalItemView(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ExerciseCountViewHolder) {
            viewHolder.bind(getInternalItem(position))
        }
    }
}
