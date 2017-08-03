package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent interface AppFragmentComponent: AndroidInjector<ExerciseFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<ExerciseFragment>()
}
