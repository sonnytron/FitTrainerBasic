package com.sonnyrodriguez.fittrainer.fittrainerbasic

import android.app.Activity
import android.app.Application
import com.sonnyrodriguez.fittrainer.fittrainerbasic.injections.AppModule
import com.sonnyrodriguez.fittrainer.fittrainerbasic.injections.DaggerDataComponent
import com.sonnyrodriguez.fittrainer.fittrainerbasic.injections.DataComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class FitTrainerApplication: Application(), HasActivityInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    lateinit var dataComponent: DataComponent

    override fun onCreate() {
        super.onCreate()

        dataComponent = DaggerDataComponent.builder().appModule(AppModule(applicationContext)).build()

        dataComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
