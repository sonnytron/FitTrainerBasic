package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import org.jetbrains.anko.find

class ExerciseViewHolder(itemView: ViewGroup): RecyclerView.ViewHolder(itemView) {
    val exerciseLabel = itemView.find<TextView>(R.id.exercise_item_title)

    fun bind(exerciseObject: ExerciseObject) {
        exerciseLabel.text = exerciseObject.title
    }
}
