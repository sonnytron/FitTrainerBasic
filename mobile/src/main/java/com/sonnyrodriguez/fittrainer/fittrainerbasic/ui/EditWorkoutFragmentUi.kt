package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.annotation.StringRes
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.text.InputType
import android.text.TextUtils
import android.view.Gravity
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseCountAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.WorkoutObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditWorkoutFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

class EditWorkoutFragmentUi(val exerciseCountAdapter: ExerciseCountAdapter, @StringRes val titleId: Int = R.string.workout_fragment_title): AnkoComponent<EditWorkoutFragment> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var workoutTitleEditText: EditText
    internal var workoutTitle: String = "Unnamed Workout"
    lateinit internal var toolbar: Toolbar
    lateinit internal var workoutActionButton: FloatingActionButton
    lateinit internal var workoutMenuButton: Button

    override fun createView(ui: AnkoContext<EditWorkoutFragment>) = with(ui) {
        verticalLayout {
            isFocusableInTouchMode = true
            toolbar = toolbar {
                setTitleTextColor(ContextCompat.getColor(ctx, R.color.colorPrimaryWhite))
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                setTitle(titleId)
                workoutMenuButton = button(R.string.common_menu_edit) {
                    setOnClickListener {
                        owner.editOrSaveWorkout()
                    }
                }
            }.lparams(width = matchParent, height = wrapContent) {

            }

            lparams(width = matchParent, height = wrapContent)
            verticalLayout {
                workoutTitleEditText = editText {
                    id = R.id.workout_title_edit
                    hint = owner.getString(R.string.workout_edit_text_hint)
                    maxLines = 1
                    lines = 1
                    ellipsize = TextUtils.TruncateAt.END
                    imeOptions = EditorInfo.IME_ACTION_DONE
                    inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                    isFocusableInTouchMode = owner.isEditing
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

                workoutActionButton = floatingActionButton {
                    id = R.id.workout_add_exercise_button
                    setImageResource(R.drawable.icon_play_black)
                    setOnClickListener {
                        owner.workoutAction()
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
        toolbar.title = workoutObject.title
    }

    internal fun protectedWorkoutTitle(): String =
            workoutTitleEditText.text.toString().let {
                if (it.isNotEmpty()) return it else return workoutTitle
            }

    internal fun switchEditMode(editEnabled: Boolean) {
        if (editEnabled) {
            workoutMenuButton.textResource = R.string.common_menu_save
            workoutActionButton.setImageResource(R.drawable.icon_add_circle_black)
        } else {
            workoutMenuButton.textResource = R.string.common_menu_edit
            workoutActionButton.setImageResource(R.drawable.icon_play_black)
        }
        workoutTitleEditText.isFocusableInTouchMode = editEnabled
    }
}
