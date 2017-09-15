package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.activities.CameraActivity
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.EditWorkoutFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent interface EditWorkoutComponent: AndroidInjector<EditWorkoutFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<EditWorkoutFragment>()
}

@Subcomponent interface CameraActivityComponent: AndroidInjector<CameraActivity> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<CameraActivity>()
}