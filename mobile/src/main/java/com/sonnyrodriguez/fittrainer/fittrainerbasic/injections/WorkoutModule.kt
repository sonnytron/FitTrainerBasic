package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.activities.CameraActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.WorkoutFragment
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(WorkoutFragmentComponent::class, CameraActivityComponent::class))
abstract class WorkoutModule {
    @Binds
    @IntoMap
    @FragmentKey(WorkoutFragment::class)
    internal abstract fun bindsWorkoutFragmentInjectorFactory(builder: WorkoutFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>

    @Binds
    @IntoMap
    @ActivityKey(CameraActivity::class)
    internal abstract fun bindsCameraActivityInjectorFactory(builder: CameraActivityComponent.Builder): AndroidInjector.Factory<out AppCompatActivity>
}
