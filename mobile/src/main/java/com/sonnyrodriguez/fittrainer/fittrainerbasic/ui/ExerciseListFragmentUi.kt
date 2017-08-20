package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseListFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ExerciseListFragmentUi(val exerciseListAdapter: ExerciseAdapter): AnkoComponent<ExerciseListFragment> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit var addCountButton: Button
    lateinit var subtractCountButton: Button
    lateinit var addSetCountButton: Button
    lateinit var subtractSetCountButton: Button
    lateinit var countTextView: TextView
    lateinit var setCountTextView: TextView
    internal var exerciseCount: Long = 8
    internal var setCount: Long = 1

    override fun createView(ui: AnkoContext<ExerciseListFragment>) = with(ui) {
        relativeLayout {
            textView {
                id = R.id.add_exercise_title
                textResource = R.string.add_exercise_title
                verticalPadding = dip(8)
            }.lparams(width = matchParent, height = wrapContent) {
                centerHorizontally()
                gravity = Gravity.CENTER
            }

            linearLayout {
                id = R.id.add_exercise_container
                subtractCountButton = button {
                    text = "-"
                    setOnClickListener {
                        decreaseCount()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
                countTextView = textView {
                    text = "1"
                }.lparams(width = wrapContent, height = wrapContent) {
                    horizontalPadding = dip(8)
                    verticalMargin = dip(8)
                    gravity = Gravity.CENTER
                }
                addCountButton = button {
                    text = "+"
                    setOnClickListener {
                        increaseCount()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                below(R.id.add_exercise_title)
                centerHorizontally()
                gravity = Gravity.CENTER
            }

            textView {
                id = R.id.add_set_title
                textResource = R.string.add_set_title
                verticalPadding = dip(8)
            }.lparams(width = matchParent, height = wrapContent) {
                centerHorizontally()
                below(R.id.add_exercise_container)
                gravity = Gravity.CENTER
            }

            linearLayout {
                id = R.id.add_set_container
                subtractSetCountButton = button {
                    text = "-"
                    setOnClickListener {
                        decreaseSet()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
                setCountTextView = textView {
                    text = "1"
                }.lparams(width = wrapContent, height = wrapContent) {
                    horizontalPadding = dip(8)
                    verticalMargin = dip(8)
                    gravity = Gravity.CENTER
                }
                addSetCountButton = button {
                    text = "+"
                    setOnClickListener {
                        increaseSet()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent) {
                centerHorizontally()
                gravity = Gravity.CENTER
                below(R.id.add_set_title)
            }

            exerciseRecyclerView = recyclerView {
                clipToPadding = false
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = this@ExerciseListFragmentUi.exerciseListAdapter.apply {
                    setOnItemClickedListener { position ->
                        owner.exerciseSelected(exerciseListAdapter.getInternalItem(position))
                    }
                }
            }.lparams(width = matchParent, height = matchParent) {
                below(R.id.add_set_container)
            }
        }
    }

    internal fun decreaseCount() {
        if (exerciseCount > 1) {
            exerciseCount--
            updateCounts()
        }
    }

    internal fun increaseCount() {
        if (exerciseCount < 65) {
            exerciseCount++
            updateCounts()
        }
    }

    internal fun increaseSet() {
        if (setCount < 65) {
            setCount++
            updateCounts()
        }
    }

    internal fun decreaseSet() {
        if (setCount > 1) {
            setCount--
            updateCounts()
        }
    }

    internal fun updateCounts() {
        setCountTextView.text = setCount.toString()
        countTextView.text = exerciseCount.toString()
    }
}
