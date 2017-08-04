package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ExerciseFragmentUi(val exerciseListAdapter: ExerciseAdapter): AnkoComponent<ExerciseFragment> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var addExerciseButton: Button
    lateinit var addExerciseEditText: EditText

    override fun createView(ui: AnkoContext<ExerciseFragment>) = with(ui) {
        verticalLayout {
            linearLayout {
                addExerciseEditText = editText {
                    hint = ctx.getString(R.string.exercise_edit_placeholder)
                }
                addExerciseButton = button {
                    text = ctx.getString(R.string.exercise_add_title)
                    setOnClickListener {
                        addExerciseEditText.text.toString().let {
                            owner.addNewExercise(it)
                        }
                    }
                }
            }.lparams(width = matchParent, height = wrapContent)
            exerciseRecyclerView = recyclerView {
                backgroundColor = ContextCompat.getColor(ctx, android.R.color.holo_orange_light)
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
