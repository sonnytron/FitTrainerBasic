package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StartWorkoutFragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StatsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent interface HistoryComponent: AndroidInjector<StatsFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<StatsFragment>()
}


@Subcomponent interface StatusComponent: AndroidInjector<StartWorkoutFragment> {
    @Subcomponent.Builder abstract class Builder: AndroidInjector.Builder<StartWorkoutFragment>()
}