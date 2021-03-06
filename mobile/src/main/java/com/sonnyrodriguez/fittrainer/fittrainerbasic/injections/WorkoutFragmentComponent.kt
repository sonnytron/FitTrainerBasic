package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface WorkoutFragmentComponent: AndroidInjector<WorkoutFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<WorkoutFragment>()
}