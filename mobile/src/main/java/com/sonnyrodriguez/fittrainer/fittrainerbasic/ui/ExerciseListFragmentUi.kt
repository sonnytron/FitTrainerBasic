package com.sonnyrodriguez.fittrainer.fittrainerbasic.ui

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseListFragment
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.relativeLayout

class ExerciseListFragmentUi(val exerciseListAdapter: ExerciseAdapter): AnkoComponent<ExerciseListFragment> {

    lateinit var exerciseRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<ExerciseListFragment>) = with(ui) {
        relativeLayout {
            exerciseRecyclerView = recyclerView {
                clipToPadding = false
                layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)
                adapter = this@ExerciseListFragmentUi.exerciseListAdapter.apply {
                    setOnItemClickedListener { position ->
                        owner.exerciseSelected(exerciseListAdapter.getInternalItem(position).id)
                    }
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}
