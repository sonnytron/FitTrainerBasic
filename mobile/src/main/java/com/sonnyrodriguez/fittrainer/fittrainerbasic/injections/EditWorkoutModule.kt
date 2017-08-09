package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditWorkoutFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(EditWorkoutComponent::class))
abstract class EditWorkoutModule {
    @Binds
    @IntoMap
    @FragmentKey(EditWorkoutFragment::class)
    internal abstract fun bindsEditWorkoutFragmentInjectorFactory(builder: EditWorkoutComponent.Builder): AndroidInjector.Factory<out Fragment>
}
