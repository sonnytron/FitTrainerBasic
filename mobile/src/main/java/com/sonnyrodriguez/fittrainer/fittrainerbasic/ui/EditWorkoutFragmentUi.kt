package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.graphics.Color
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.EditText
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditWorkoutFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EditWorkoutFragmentUi(val exerciseAdapter: ExerciseAdapter): AnkoComponent<EditWorkoutFragment> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var workoutTitleEditText: EditText
    lateinit var addExerciseButton: FloatingActionButton
    internal var workoutTitle: String = "Unnamed Workout"

    override fun createView(ui: AnkoContext<EditWorkoutFragment>) = with(ui) {
        coordinatorLayout {
            appBarLayout {
                toolbar {
                    id = R.id.general_toolbar_id
                    setTitleTextColor(Color.WHITE)
                    setTitle(R.string.workout_fragment_title)
                }.lparams(width = matchParent, height = matchParent)

                button(R.string.common_save) {
                    setOnClickListener {
                        owner.saveWorkoutAndExit()
                    }
                }
            }.lparams(width = matchParent)
            linearLayout {
                textInputLayout {
                    workoutTitleEditText = editText {
                        id = R.id.workout_title_edit
                        hint = owner.getString(R.string.workout_edit_text_hint)
                    }
                }.lparams(width = matchParent, height = wrapContent)
            }.lparams(width = matchParent, height = wrapContent)

            exerciseRecyclerView = recyclerView {
                id = R.id.workout_exercise_recycler
                clipToPadding = false
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = this@EditWorkoutFragmentUi.exerciseAdapter
            }.lparams(width = matchParent, height = matchParent)

            addExerciseButton = floatingActionButton {
                id = R.id.workout_add_exercise_button
                setImageResource(R.drawable.icon_add_circle_black)
                setOnClickListener {
                    owner.addNewExercise()
                }
            }.lparams(width = wrapContent, height = wrapContent) {
                gravity = Gravity.BOTTOM or Gravity.END
                margin = dip(16)
                anchorId = R.id.workout_exercise_recycler
                anchorGravity = Gravity.BOTTOM or Gravity.END
            }
        }
    }

    internal fun protectedWorkoutTitle(): String =
            workoutTitleEditText.text.toString().let {
                if (it.length > 0) return it else return workoutTitle
            }
}
