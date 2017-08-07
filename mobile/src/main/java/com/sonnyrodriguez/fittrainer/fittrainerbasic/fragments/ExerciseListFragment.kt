package com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments

import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject

class ExerciseListFragment: Fragment() {



    companion object {
        fun newInstance(exerciseList: List<ExerciseObject>) = ExerciseListFragment().apply {

        }
    }

}
