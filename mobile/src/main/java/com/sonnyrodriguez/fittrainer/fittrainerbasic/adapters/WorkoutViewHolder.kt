package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import org.jetbrains.anko.find

class WorkoutViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(itemView) {
    val workoutTitle: TextView = itemView.find(R.id.workout_item_title)
    val exerciseCount: TextView = itemView.find(R.id.workout_exercise_count)

    fun bind(workoutObject: WorkoutObject) {
        workoutTitle.text = workoutObject.title
        exerciseCount.text = FitTrainerApplication.instance.getString(R.string.workout_exercise_count_label, workoutObject.exerciseMetaList.count())
    }
}
