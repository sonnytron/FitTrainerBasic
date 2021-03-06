package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditExerciseFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(AppFragmentComponent::class, EditExerciseComponent::class))
abstract class ExerciseModule {
    @Binds
    @IntoMap
    @FragmentKey(ExerciseFragment::class)
    internal abstract fun bindsExerciseFragmentInjectorFactory(builder: AppFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(EditExerciseFragment::class)
    internal abstract fun bindsEditExerciseFragmentInjectorFactory(builder: EditExerciseComponent.Builder): AndroidInjector.Factory<out Fragment>
}
