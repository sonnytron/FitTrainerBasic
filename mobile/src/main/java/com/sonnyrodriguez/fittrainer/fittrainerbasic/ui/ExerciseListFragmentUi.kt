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
    lateinit var countTextView: TextView

    override fun createView(ui: AnkoContext<ExerciseListFragment>) = with(ui) {
        relativeLayout {
            linearLayout {
                id = R.id.add_exercise_container
                subtractCountButton = button {
                    text = "-"
                    setOnClickListener {
                        owner.decreaseCount()
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
                        owner.increaseCount()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    horizontalMargin = dip(16)
                    verticalMargin = dip(8)
                }
            }.lparams(width = matchParent, height = wrapContent)

            exerciseRecyclerView = recyclerView {
                clipToPadding = false
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = this@ExerciseListFragmentUi.exerciseListAdapter.apply {
                    setOnItemClickedListener { position ->
                        owner.exerciseSelected(exerciseListAdapter.getInternalItem(position))
                    }
                }
            }.lparams(width = matchParent, height = matchParent) {
                below(R.id.add_exercise_container)
            }
        }
    }
}
