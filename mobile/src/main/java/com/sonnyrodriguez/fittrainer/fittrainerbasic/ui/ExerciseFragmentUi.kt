package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ExerciseFragmentUi(val exerciseListAdapter: ExerciseAdapter): AnkoComponent<ExerciseFragment> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var addExerciseButton: Button
    lateinit var addExerciseEditText: EditText
    lateinit var muscleSpinner: Spinner

    override fun createView(ui: AnkoContext<ExerciseFragment>) = with(ui) {
        verticalLayout {
            linearLayout {
                addExerciseEditText = editText {
                    hint = ctx.getString(R.string.exercise_edit_placeholder)
                }

                muscleSpinner = spinner {

                }

                addExerciseButton = button {
                    text = ctx.getString(R.string.exercise_add_title)
                    setOnClickListener {
                        addExerciseEditText.text.toString().let {
                            owner.addNewExercise(it, muscleSpinner.selectedItemPosition)
                        }
                    }
                }
            }.lparams(width = matchParent, height = wrapContent)
            exerciseRecyclerView = recyclerView {
                clipToPadding = false
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = this@ExerciseFragmentUi.exerciseListAdapter.apply {
                    setOnItemClickedListener { position ->

                    }
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}
