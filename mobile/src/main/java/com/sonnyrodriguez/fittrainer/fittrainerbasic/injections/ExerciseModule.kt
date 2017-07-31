package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.app.Activity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(AppSubComponent::class))
abstract class ExerciseModule {
    @Binds
    @IntoMap
    @ActivityKey(ExerciseActivity::class)
    internal abstract fun bindsExerciseActivityInjectorFactory(builder: AppSubComponent.Builder): AndroidInjector.Factory<out Activity>
}
