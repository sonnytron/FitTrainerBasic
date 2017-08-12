package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditExerciseFragment
import org.jetbrains.anko.*

class EditExerciseFragmentUi: AnkoComponent<EditExerciseFragment> {

    lateinit var muscleSpinner: Spinner
    lateinit var exerciseTitleEdit: EditText
    lateinit var saveButton: Button

    override fun createView(ui: AnkoContext<EditExerciseFragment>) = with(ui) {
        relativeLayout {
            exerciseTitleEdit = editText {
                id = R.id.exercise_edit_title
                setHint(R.string.exercise_add_title)
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
            }

            muscleSpinner = spinner {
                id = R.id.exercise_edit_spinner
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
                below(R.id.exercise_edit_title)
            }

            saveButton = button {
                id = R.id.exercise_edit_save
                setText(R.string.common_save)
                setOnClickListener {
                    owner.saveExercise()
                }
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
                below(R.id.exercise_edit_spinner)
            }
        }
    }

    internal fun updateUi(title: String, spinnerIndex: Int) {
        exerciseTitleEdit.setText(title)
        muscleSpinner.setSelection(spinnerIndex)
    }
}
