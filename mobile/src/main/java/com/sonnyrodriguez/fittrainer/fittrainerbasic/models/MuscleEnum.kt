package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import android.arch.persistence.room.TypeConverter
import com.sonnyrodriguez.fittrainer.fittrainerbasic.FitTrainerApplication
import com.sonnyrodriguez.fittrainer.fittrainerbasic.R

enum class MuscleEnum(val title: String) {
    CHEST(FitTrainerApplication.instance.getString(R.string.muscle_chest)),
    BICEPS(FitTrainerApplication.instance.getString(R.string.muscle_biceps)),
    TRICEPS(FitTrainerApplication.instance.getString(R.string.muscle_triceps)),
    SHOULDERS(FitTrainerApplication.instance.getString(R.string.muscle_shoulders)),
    BACK(FitTrainerApplication.instance.getString(R.string.muscle_back)),
    ABS(FitTrainerApplication.instance.getString(R.string.muscle_abs)),
    QUADS(FitTrainerApplication.instance.getString(R.string.muscle_quads)),
    HAMS(FitTrainerApplication.instance.getString(R.string.muscle_hams)),
    CALVES(FitTrainerApplication.instance.getString(R.string.muscle_calves));

    companion object {
        fun fromMuscleNumber(number: Int): MuscleEnum {
            values().find { it.ordinal == number }?.let {
                return it
            }
            return CHEST
        }

        fun stringFromMuscle(muscleEnum: MuscleEnum): String {
            return muscleEnum.title
        }

        fun fromMuscle(muscleEnum: MuscleEnum): Int {
            return muscleEnum.ordinal
        }
    }


}
