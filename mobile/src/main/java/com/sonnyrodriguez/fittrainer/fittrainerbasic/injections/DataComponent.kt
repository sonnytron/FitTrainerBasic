package com.sonnyrodriguez.fittrainer.fittrainerbasic.injections

import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import dagger.Component

@Component(modules = arrayOf(AppModule::class,
        ExerciseModule::class,
        WorkoutModule::class))
interface DataComponent {
    fun inject(fitTrainerApplication: FitTrainerApplication)
}
