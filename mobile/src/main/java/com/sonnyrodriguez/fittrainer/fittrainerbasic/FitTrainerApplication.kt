package com.sonnyrodriguez.fittrainer.fittrainerbasic

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.sonnyrodriguez.fittrainer.fittrainerbasic.injections.AppModule
import com.sonnyrodriguez.fittrainer.fittrainerbasic.injections.DaggerDataComponent
import com.sonnyrodriguez.fittrainer.fittrainerbasic.injections.DataComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class FitTrainerApplication: Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var dataComponent: DataComponent

    companion object {
        lateinit var instance: FitTrainerApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        dataComponent = DaggerDataComponent.builder().appModule(AppModule(applicationContext)).build()

        dataComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}
