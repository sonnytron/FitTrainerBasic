package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.content.res.ColorStateList
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.ImageView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.WorkoutAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

class WorkoutFragmentUi(val workoutListAdapter: WorkoutAdapter): AnkoComponent<WorkoutFragment> {

    lateinit var workoutRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<WorkoutFragment>) = with(ui) {
        verticalLayout {
            toolbar {
                setTitleTextColor(ContextCompat.getColor(ctx, R.color.colorPrimaryWhite))
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                setTitle(R.string.navigation_workout_title)
            }
            coordinatorLayout {
                lparams(width = matchParent, height = matchParent)
                workoutRecyclerView = recyclerView {
                    id = R.id.workout_recycler_view
                    clipToPadding = false
                    layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                    adapter = this@WorkoutFragmentUi.workoutListAdapter.apply {
                        setOnItemClickedListener { position ->
                            owner.editWorkout(workoutListAdapter.getInternalItem(position))
                        }
                    }
                }.lparams(width = matchParent, height = matchParent)

                floatingActionButton {
                    id = R.id.workout_add_workout_button
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorPrimary))
                    setImageResource(R.drawable.icon_add_black)
                    setColorFilter(ContextCompat.getColor(ctx, R.color.colorPrimaryWhite))
                    scaleType = ImageView.ScaleType.CENTER
                    setOnClickListener {
                        owner.addNewWorkout()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.BOTTOM or Gravity.END
                    margin = dip(16)
                    anchorId = R.id.workout_recycler_view
                    anchorGravity = Gravity.BOTTOM or Gravity.END
                }
            }
        }

    }
}
