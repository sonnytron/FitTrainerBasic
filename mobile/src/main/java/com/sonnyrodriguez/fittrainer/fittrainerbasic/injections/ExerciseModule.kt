package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditWorkoutFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.ExerciseFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(AppFragmentComponent::class))
abstract class ExerciseModule {
    @Binds
    @IntoMap
    @FragmentKey(ExerciseFragment::class)
    internal abstract fun bindsExerciseFragmentInjectorFactory(builder: AppFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}

@Module(subcomponents = arrayOf(WorkoutFragmentComponent::class))
abstract class WorkoutModule {
    @Binds
    @IntoMap
    @FragmentKey(WorkoutFragment::class)
    internal abstract fun bindsWorkoutFragmentInjectorFactory(builder: WorkoutFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @FragmentKey(EditWorkoutFragment::class)
    internal abstract fun bindsEditWorkoutFragmentInjectorFactory(builder: EditWorkoutComponent.Builder): AndroidInjector.Factory<out Fragment>
}
