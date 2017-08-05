package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import org.jetbrains.anko.find

class ExerciseViewHolder(itemView: ViewGroup): RecyclerView.ViewHolder(itemView) {
    val exerciseLabel = itemView.find<TextView>(R.id.exercise_item_title)
    val muscleLabel = itemView.find<TextView>(R.id.exercise_item_muscle)

    fun bind(exerciseObject: ExerciseObject) {
        exerciseLabel.text = exerciseObject.title
        MuscleEnum.fromMuscleNumber(exerciseObject.muscleGroupNumber).let {
            muscleLabel.text = MuscleEnum.stringFromMuscle(it)
        }

    }
}
