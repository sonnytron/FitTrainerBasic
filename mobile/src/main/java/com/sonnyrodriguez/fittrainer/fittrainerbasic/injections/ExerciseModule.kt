package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.app.Activity
import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.ExerciseActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(AppSubComponent::class, AppFragmentComponent::class))
abstract class ExerciseModule {
    @Binds
    @IntoMap
    @ActivityKey(ExerciseActivity::class)
    internal abstract fun bindsExerciseActivityInjectorFactory(builder: AppSubComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @FragmentKey(ExerciseFragment::class)
    internal abstract fun bindsExerciseFragmentInjectorFactory(builder: AppFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}
