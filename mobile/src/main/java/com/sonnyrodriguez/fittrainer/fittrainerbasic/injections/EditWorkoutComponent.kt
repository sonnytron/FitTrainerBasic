package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditWorkoutFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent interface EditWorkoutComponent: AndroidInjector<EditWorkoutFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<EditWorkoutFragment>()
}
