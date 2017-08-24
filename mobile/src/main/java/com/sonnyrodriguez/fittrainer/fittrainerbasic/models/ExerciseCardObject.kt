package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

data class ExerciseCardObject(val title: String,
                              val imageList: List<String> = arrayListOf(),
                              val muscleInt: Int,
                              val count: Int = 1,
                              val exerciseId: Long,
                              var completed: Boolean = false)
