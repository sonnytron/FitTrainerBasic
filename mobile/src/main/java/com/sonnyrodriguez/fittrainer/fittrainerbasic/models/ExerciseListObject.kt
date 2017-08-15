package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import com.sonnyrodriguez.fittrainer.fittrainerbasic.database.ExerciseObject

data class ExerciseListObject(val exerciseObject: ExerciseObject,
                              var exerciseCount: Long)
