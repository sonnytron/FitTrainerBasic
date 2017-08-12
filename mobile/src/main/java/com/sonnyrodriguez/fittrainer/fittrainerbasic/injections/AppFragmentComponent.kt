package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditExerciseFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent interface AppFragmentComponent: AndroidInjector<ExerciseFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<ExerciseFragment>()
}

@Subcomponent interface EditExerciseComponent: AndroidInjector<EditExerciseFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<EditExerciseFragment>()
}
