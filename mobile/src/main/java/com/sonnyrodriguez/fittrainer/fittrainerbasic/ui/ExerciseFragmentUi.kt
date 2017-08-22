package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ExerciseFragmentUi(val exerciseListAdapter: ExerciseAdapter): AnkoComponent<ExerciseFragment> {

    lateinit var exerciseRecyclerView: RecyclerView
    lateinit internal var toolbar: Toolbar

    override fun createView(ui: AnkoContext<ExerciseFragment>) = with(ui) {
        verticalLayout {
            toolbar = toolbar {
                setTitleTextColor(ContextCompat.getColor(ctx, R.color.colorPrimaryWhite))
                backgroundColor = ContextCompat.getColor(ctx, R.color.colorPrimary)
                setTitle(R.string.navigation_exercise_title)
            }
            coordinatorLayout {
                lparams(width = matchParent, height = matchParent)
                exerciseRecyclerView = recyclerView {
                    id = R.id.exercise_recycler_view
                    clipToPadding = false
                    layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                    adapter = this@ExerciseFragmentUi.exerciseListAdapter.apply {
                        setOnItemClickedListener { position ->
                            owner.exerciseSelected(exerciseListAdapter.getInternalItem(position))
                        }
                    }
                }.lparams(width = matchParent, height = matchParent)
                floatingActionButton {
                    id = R.id.exercise_add_exercise_button
                    setImageResource(R.drawable.icon_add_black)
                    backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.colorPrimary))
                    setColorFilter(ContextCompat.getColor(ctx, R.color.colorPrimaryWhite))
                    scaleType = ImageView.ScaleType.CENTER
                    setOnClickListener {
                        owner.addNewExercise()
                    }
                }.lparams(width = wrapContent, height = wrapContent) {
                    gravity = Gravity.BOTTOM or Gravity.END
                    margin = dip(16)
                    anchorId = R.id.exercise_recycler_view
                    anchorGravity = Gravity.BOTTOM or Gravity.END
                }
            }
        }


    }
}
