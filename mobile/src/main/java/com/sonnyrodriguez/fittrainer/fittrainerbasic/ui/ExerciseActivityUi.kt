package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ExerciseActivityUi: AnkoComponent<ExerciseActivity> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var editExerciseText: EditText
    lateinit var addExerciseButton: Button

    override fun createView(ui: AnkoContext<ExerciseActivity>) = with(ui) {
        relativeLayout {
            cardView {
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    editExerciseText = editText {
                        hint = owner.getString(R.string.exercise_edit_placeholder)
                    }
                    addExerciseButton = button {
                        text = owner.getString(R.string.exercise_add_title)
                        setOnClickListener {

                        }
                    }
                }
            }.lparams(matchParent, height = wrapContent) {
                margin = dip(8)
                alignParentTop()
            }
            exerciseRecyclerView = recyclerView {
                clipToPadding = false
            }.lparams(width = matchParent, height = matchParent) {
                setPadding(4, 36, 4, 4)
            }
        }
    }
}
