package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent interface AppSubComponent: AndroidInjector<ExerciseActivity> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<ExerciseActivity>()
}
