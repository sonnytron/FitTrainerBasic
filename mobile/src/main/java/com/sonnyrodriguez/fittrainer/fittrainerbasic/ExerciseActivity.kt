package com.sonnyrodriguez.fittrainer.fittrainerbasic

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.models.MuscleGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.presenter.ExercisePresenterHelper
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.ExerciseActivityUi
import dagger.android.AndroidInjection
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.ctx
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import javax.inject.Inject

class ExerciseActivity: AppCompatActivity(), ExercisePresenter {
    private lateinit var ui: ExerciseActivityUi

    internal val exerciseAdapter: ExerciseAdapter = ExerciseAdapter()

    @Inject lateinit var exerciseHelper: ExercisePresenterHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        ui = ExerciseActivityUi(exerciseAdapter) { exerciseSelected ->
            viewExerciseObject(exerciseSelected)
        }
        ui.setContentView(this)
    }

    override fun onDestroy() {
        exerciseHelper.onDestroy()
        super.onDestroy()
    }

    override fun showExercises(exercises: List<ExerciseObject>) {
        exerciseAdapter.changeAll(exercises)
    }

    override fun exerciseAddedTo(position: Int) {
        exerciseAdapter.notifyItemChanged(position)
        exerciseAdapter.notifyDataSetChanged()
    }

    override fun scrollTo(position: Int) {
        ui.exerciseRecyclerView.scrollToPosition(position)
    }

    internal fun viewExerciseObject(exerciseObject: ExerciseObject) {
        // TODO: Implement view/edit Exercise fragment
    }

    internal fun addNewExercise(title: String) {
        exerciseHelper.addNewExercise(title, MuscleGroup.CHEST.ordinal)
        toast("Exercise Added! $title")
    }

}
