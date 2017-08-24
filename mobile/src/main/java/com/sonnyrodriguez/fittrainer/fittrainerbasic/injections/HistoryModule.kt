package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.fragments.StatsFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@Module(subcomponents = arrayOf(HistoryComponent::class))
abstract class HistoryModule {
    @Binds
    @IntoMap
    @FragmentKey(StatsFragment::class)
    internal abstract fun bindsStatsFragmentInjectorFactory(builder: HistoryComponent.Builder): AndroidInjector.Factory<out Fragment>
}
