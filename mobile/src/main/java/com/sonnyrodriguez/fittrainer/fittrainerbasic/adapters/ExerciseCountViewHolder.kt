package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.ExerciseListObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleEnum
import org.jetbrains.anko.find

class ExerciseCountViewHolder(itemView: ViewGroup): RecyclerView.ViewHolder(itemView) {
    val exerciseTitle: TextView = itemView.find(R.id.exercise_count_item_title)
    val exerciseMuscle: TextView = itemView.find(R.id.exercise_count_item_muscle)
    val exerciseCount: TextView = itemView.find(R.id.exercise_count_item_count)

    fun bind(exerciseListObject: ExerciseListObject) {
        exerciseTitle.text = exerciseListObject.exerciseObject.title
        exerciseCount.text = exerciseListObject.exerciseCount.toString()
        MuscleEnum.fromMuscleNumber(exerciseListObject.exerciseObject.muscleGroupNumber).let {
            exerciseMuscle.text = it.title
        }
    }
}
