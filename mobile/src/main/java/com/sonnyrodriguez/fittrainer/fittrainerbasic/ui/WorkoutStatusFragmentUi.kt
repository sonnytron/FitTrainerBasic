package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutStatusFragment
import org.jetbrains.anko.*

class WorkoutStatusFragmentUi(var muscleGroups: ArrayList<String> = arrayListOf()): AnkoComponent<WorkoutStatusFragment> {
    lateinit var muscleGroupTextView: TextView
    lateinit var muscleCountText: TextView
    lateinit var exerciseTitleText: TextView

    override fun createView(ui: AnkoContext<WorkoutStatusFragment>) = with(ui) {
        relativeLayout {
            verticalLayout {
                exerciseTitleText = themedTextView(R.style.WorkoutTitle) {

                }
                muscleCountText = themedTextView(R.style.WorkoutSubtitle) {
                    text = context.getString(R.string.exercise_count_title, 1)
                }
                muscleGroupTextView = themedTextView(R.style.WorkoutSubtitle) {
                    text = context.getString(R.string.exercise_muscle_title)
                }
            }
        }
    }

    internal fun setMuscleGroupText(title: String, muscleCountString: String) {
        var muscleGroupString = ""
        muscleGroups.forEachIndexed { index, value ->
            if (index == muscleGroups.count() - 1) {
                muscleGroupString = "$muscleGroupString, $value"
            } else {
                muscleGroupString = "$muscleGroupString, $value,"
            }
        }
        val muscleGroupValue = "${FitTrainerApplication.instance.getString(R.string.exercise_muscle_title)}$muscleGroupString"
        muscleGroupTextView.text = muscleGroupValue
        exerciseTitleText.text = title
        muscleCountText.text = muscleCountString
    }
}
