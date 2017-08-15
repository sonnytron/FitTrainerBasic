package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sonnyrodriguez.fittrainer.fittrainerbasic.adapters.ExerciseAdapter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ui.ExerciseListFragmentUi
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.KeyConstants
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class ExerciseListFragment: Fragment() {

    lateinit internal var ui: ExerciseListFragmentUi
    internal var exerciseCount = 8
    // TODO: Add plus and minus button to Exercise List Fragment

    internal var exerciseAdapter: ExerciseAdapter = ExerciseAdapter()

    companion object {
        fun newInstance(exerciseList: List<ExerciseObject>) = ExerciseListFragment().apply {
            exerciseAdapter.addAll(exerciseList)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        ui = ExerciseListFragmentUi(exerciseAdapter)
        return ui.createView(AnkoContext.Companion.create(ctx, this)).apply {
            ui.countTextView.text = exerciseCount.toString()
        }
    }

    // TODO: Add long for count to target fragment activity result

    internal fun decreaseCount() {
        if (exerciseCount > 0) {
            exerciseCount--
            ui.countTextView.text = exerciseCount.toString()
        }
    }

    internal fun increaseCount() {
        if (exerciseCount < 65) {
            exerciseCount++
            ui.countTextView.text = exerciseCount.toString()
        }
    }

    internal fun exerciseSelected(exerciseObject: ExerciseObject) {
        targetFragment?.let { targetFrag ->
            val intent = Intent()
            intent.putExtra(KeyConstants.KEY_RESULT_KEY_LONG, exerciseObject.id)
            intent.putExtra(KeyConstants.KEY_RESULT_LONG, exerciseCount)
            intent.putExtra(KeyConstants.KEY_RESULT_TEXT, exerciseObject.title)
            targetFrag.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            fragmentManager.popBackStack()
        }
    }
}
