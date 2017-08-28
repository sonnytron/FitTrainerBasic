package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StartWorkoutFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.LocalExerciseObject
import org.jetbrains.anko.*

class StartWorkoutFragmentUi(var currentExercise: LocalExerciseObject? = null,
                             val workoutTitle: String): AnkoComponent<StartWorkoutFragment> {

    lateinit var currentExerciseTitle: TextView
    lateinit var currentExerciseCount: TextView
    lateinit var currentExerciseSet: TextView
    lateinit var exerciseImageView: ImageView
    lateinit var forwardAction: Button
    lateinit var backwardAction: Button
    lateinit var stopAction: Button
    internal var currentSetCount = 0L

    override fun createView(ui: AnkoContext<StartWorkoutFragment>) = with(ui) {
        relativeLayout {
            verticalLayout {
                currentExerciseTitle = themedTextView(R.style.WorkoutTitle) {
                    text = workoutTitle
                }.lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(12)
                    verticalMargin = dip(8)
                }
                exerciseImageView = imageView {

                }.lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
                currentExerciseCount = themedTextView(R.style.WorkoutSubtitle) {
                    text = exerciseCountString()
                }.lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
                currentExerciseSet = themedTextView(R.style.WorkoutSubtitle) {
                    text = exerciseSetString()
                }.lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
                linearLayout {
                    backwardAction = button {
                        text = ctx.getString(R.string.exercise_skipped_rep)
                        setOnClickListener {
                            owner.backwardAction(currentExercise)
                        }
                    }.lparams(width = wrapContent, height = wrapContent) {
                        rightMargin = dip(8)
                    }
                    forwardAction = button {
                        text = ctx.getString(R.string.exercise_completed_rep)
                        setOnClickListener {
                            owner.forwardAction(currentExercise)
                        }
                    }.lparams(width = wrapContent, height = wrapContent) {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
                stopAction = button {
                    text = ctx.getString(R.string.exercise_finished)
                    setOnClickListener {
                        owner.stopAction()
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                centerHorizontally()
                alignParentTop()
            }
        }
    }

    internal fun exerciseCountString(): String {
        if (currentExercise != null) {
            return FitTrainerApplication.instance.getString(R.string.exercise_rep_count, currentExercise?.count)
        } else {
            return FitTrainerApplication.instance.getString(R.string.exercise_default_title)
        }
    }

    internal fun exerciseSetString(): String {
        if (currentExercise != null) {
            return FitTrainerApplication.instance.getString(R.string.exercise_set_count, currentExercise?.set)
        } else {
            return FitTrainerApplication.instance.getString(R.string.exercise_set_default_title)
        }
    }

    internal fun updateUi() {
        currentExercise?.let {
            currentExerciseTitle.text = it.title
            currentExerciseCount.text = exerciseCountString()
            currentExerciseSet.text = exerciseSetString()
        }
    }
}
