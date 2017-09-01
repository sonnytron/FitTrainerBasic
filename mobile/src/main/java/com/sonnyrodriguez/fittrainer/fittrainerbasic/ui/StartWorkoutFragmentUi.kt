package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
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

    lateinit var muscleGroupTextView: TextView
    lateinit var muscleCountText: TextView
    lateinit var exerciseTitleText: TextView

    lateinit var startAction: Button

    lateinit var workoutStatusView: LinearLayout
    lateinit var currentExerciseView: LinearLayout

    var workoutCompleted = false

    var muscleGroups: ArrayList<String> = arrayListOf()

    override fun createView(ui: AnkoContext<StartWorkoutFragment>) = with(ui) {
        relativeLayout {
            verticalLayout {
                workoutStatusView = verticalLayout {
                    exerciseTitleText = themedTextView(R.style.WorkoutTitle) {

                    }
                    muscleCountText = themedTextView(R.style.WorkoutSubtitle) {
                        text = context.getString(R.string.exercise_count_title, 1)
                    }
                    muscleGroupTextView = themedTextView(R.style.WorkoutSubtitle) {
                        text = context.getString(R.string.exercise_muscle_title)
                    }
                    startAction = button {
                        text = ctx.getString(R.string.exercise_start_workout)
                        setOnClickListener {
                            owner.workoutAction(workoutCompleted)
                        }
                    }.lparams(width = matchParent, height = wrapContent) {
                        horizontalMargin = dip(16)
                        verticalMargin = dip(8)
                    }
                }.lparams(width = matchParent, height = wrapContent) {
                    horizontalMargin = dip(16)
                }

                currentExerciseView = verticalLayout {
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
                }.lparams(width = matchParent, height = wrapContent)
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

    internal fun updateStatus(withFinished: Boolean, completedSets: Int) {
        currentExerciseView.visibility = View.GONE
        workoutStatusView.visibility = View.VISIBLE
        exerciseTitleText.text = FitTrainerApplication.instance.getString(R.string.exercise_status_complete)
        muscleCountText.text = FitTrainerApplication.instance.getString(R.string.exercise_status_set_count, completedSets)
        muscleGroupTextView.text = FitTrainerApplication.instance.getString(R.string.exercise_status_muscle_count, muscleGroups.count())
        startAction.text = FitTrainerApplication.instance.getString(R.string.exercise_status_save_workout)
        workoutCompleted = withFinished
    }

    internal fun updateUi() {
        currentExercise?.let {
            currentExerciseTitle.text = it.title
            currentExerciseCount.text = exerciseCountString()
            currentExerciseSet.text = exerciseSetString()
            currentExerciseView.visibility = View.VISIBLE
            workoutStatusView.visibility = View.GONE
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
        currentExerciseView.visibility = View.GONE
    }
}
