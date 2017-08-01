package com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import org.jetbrains.anko.AnkoContext

class ExerciseAdapter: RecyclerView.Adapter<ExerciseViewHolder>() {
    val exerciseList: List<ExerciseObject> = ArrayList<ExerciseObject>()

    val isEmpty: Boolean
        get() = exerciseList.isEmpty()

    val isNotEmpty: Boolean
        get() = !isEmpty

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        return ExerciseViewHolder(ExerciseItemUi().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return exerciseList.count()
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(exerciseList[position])
    }
}