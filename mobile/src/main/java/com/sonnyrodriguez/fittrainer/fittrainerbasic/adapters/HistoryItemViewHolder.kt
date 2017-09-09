package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutHistoryObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalStatObject
import org.jetbrains.anko.find

class HistoryItemViewHolder(itemView: ViewGroup): RecyclerView.ViewHolder(itemView) {

    val titleLabel = itemView.find<TextView>(R.id.history_item_title)
    val completedLabel = itemView.find<TextView>(R.id.history_item_completed)
    val countLabel = itemView.find<TextView>(R.id.history_item_count)
    val durationLabel = itemView.find<TextView>(R.id.history_item_duration)

    fun bind(localStatObject: LocalStatObject) {
        titleLabel.text = localStatObject.title
        completedLabel.text = localStatObject.dateString
        countLabel.text = localStatObject.totalExercisesString
        durationLabel.text = localStatObject.durationString
    }
}
