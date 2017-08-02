package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ExerciseActivityUi(val exerciseAdapter: ExerciseAdapter, val onExerciseSelected: (exerciseObject: ExerciseObject) -> Unit): AnkoComponent<ExerciseActivity> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var editExerciseText: EditText
    lateinit var addExerciseButton: Button

    override fun createView(ui: AnkoContext<ExerciseActivity>) = with(ui) {
        verticalLayout {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                editExerciseText = editText {
                    hint = owner.getString(R.string.exercise_edit_placeholder)
                }
                addExerciseButton = button {
                    text = owner.getString(R.string.exercise_add_title)
                    setOnClickListener({
                        editExerciseText.text.toString().let { newExerciseString ->
                            if (newExerciseString.isNotBlank()) {
                                owner.addNewExercise(newExerciseString)
                            }
                        }
                    })
                }
            }
            exerciseRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = this@ExerciseActivityUi.exerciseAdapter.apply {
                    setOnItemClickedListener { position ->
                        owner.viewExerciseObject(owner.exerciseAdapter.getInternalItem(position))
                    }
                }
                clipToPadding = false
            }.lparams(width = matchParent, height = matchParent) {
                setPadding(4, 36, 4, 4)
            }
        }
    }
}
