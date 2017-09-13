package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.widget.*
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditExerciseFragment
import org.jetbrains.anko.*

class EditExerciseFragmentUi: AnkoComponent<EditExerciseFragment> {

    lateinit var muscleSpinner: Spinner
    lateinit var exerciseTitleEdit: EditText
    lateinit var saveButton: Button
    lateinit var imageContainer: LinearLayout
    lateinit var imageTitleView: TextView
    lateinit var firstImage: ImageView
    lateinit var secondImage: ImageView
    lateinit var thirdImage: ImageView

    override fun createView(ui: AnkoContext<EditExerciseFragment>) = with(ui) {
        relativeLayout {
            exerciseTitleEdit = editText {
                id = R.id.exercise_edit_title
                setHint(R.string.exercise_add_title)
            }.lparams(width = matchParent, height = wrapContent) {
                topMargin = dip(16)
                margin = dip(8)
            }

            muscleSpinner = spinner {
                id = R.id.exercise_edit_spinner
            }.lparams(width = matchParent, height = wrapContent) {
                margin = dip(8)
                below(R.id.exercise_edit_title)
            }

            imageTitleView = themedTextView(R.style.WorkoutTitle) {
                setText(R.string.exercise_images_title)
            }.lparams(width = matchParent, height = wrapContent) {
                verticalMargin = dip(8)
                centerHorizontally()
            }

            imageContainer = linearLayout {
                id = R.id.exercise_edit_image_view
            }.lparams(width = matchParent, height = dimen(R.dimen.exercise_edit_image_dim) + dip(32))

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
