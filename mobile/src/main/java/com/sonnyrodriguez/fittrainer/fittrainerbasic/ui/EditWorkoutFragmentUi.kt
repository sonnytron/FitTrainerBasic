package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.graphics.Color
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.EditText
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseCountAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditWorkoutFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.appBarLayout
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EditWorkoutFragmentUi(val exerciseCountAdapter: ExerciseCountAdapter): AnkoComponent<EditWorkoutFragment> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var workoutTitleEditText: EditText
    lateinit var addExerciseButton: FloatingActionButton
    internal var workoutTitle: String = "Unnamed Workout"

    override fun createView(ui: AnkoContext<EditWorkoutFragment>) = with(ui) {
        verticalLayout {
            lparams(width = matchParent, height = wrapContent)
            verticalLayout {
                workoutTitleEditText = editText {
                    id = R.id.workout_title_edit
                    hint = owner.getString(R.string.workout_edit_text_hint)
                }.lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }
                button(R.string.common_save) {
                    setOnClickListener {
                        owner.saveWorkoutAndExit()
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    margin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent)
            coordinatorLayout {
                exerciseRecyclerView = recyclerView {
                    id = R.id.workout_exercise_recycler
                    clipToPadding = false
                    layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                    adapter = this@EditWorkoutFragmentUi.exerciseCountAdapter
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
    }

    internal fun updateUi(workoutObject: WorkoutObject) {
        workoutTitleEditText.setText(workoutObject.title)
    }

    internal fun protectedWorkoutTitle(): String =
            workoutTitleEditText.text.toString().let {
                if (it.length > 0) return it else return workoutTitle
            }
}
