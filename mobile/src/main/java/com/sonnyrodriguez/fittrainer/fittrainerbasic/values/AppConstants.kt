package com.sonnyrodriguez.fittrainer.fittrainerbasic.values

class DataConstants {
    companion object {
        const val exerciseDbName = "local-exc-db"
    }
}

class RequestConstants {
    companion object {
        const val ADD_EXERCISE_CONSTANT = 1
        const val ADD_WORKOUT_CONSTANT = 2
        const val NEW_EXERCISE_CONSTANT = 3
        const val EDIT_EXERCISE_CONSTANT = 4
        const val INTENT_EXERCISE_LIST = 5
    }
}

class KeyConstants {
    companion object {
        const val KEY_RESULT_TEXT = "keyResultText"
        const val KEY_RESULT_KEY_LONG = "keyResultKeyLong"
        const val KEY_RESULT_LONG = "keyResultLong"
        const val KEY_RESULT_SET = "keyResultSet"
        const val KEY_RESULT_BOOLEAN = "keyResultBoolean"
        const val KEY_RESULT_INT = "keyResultInt"
        const val LOCAL_EXERCISE_LIST = "localExerciseList"
        const val WORKOUT_TITLE_TEXT = "workoutTitleValue"
        const val STRING_ARRAY_EXTRA = "stringArrayExtra"
        const val INTENT_WORKOUT_OBJECT = "intentWorkoutObject"
        const val INTENT_EXERCISE_ID = "cameraIntentExerciseId"
        const val INTENT_EXERCISE_INDEX = "cameraIntentExerciseIndex"
        const val INTENT_COMPLETED_EXERCISES = "intentCompletedExercises"
        const val INTENT_COMPLETE_WORKOUT = "intentCompleteWorkout"
    }
}

class UIConstants {
    companion object {
        const val FILE_PATH_SEPARATOR = "/"
        const val FILE_INTERMEDIATE_NAME = "exercise_image.png"
        const val DEFAULT_IMAGE_QUALITY = 100
        const val DEFAULT_FILE_PATH_VALUE_BAR = "_"
    }
}

class ConverterConstants {
    companion object {
        const val OBJECT_SEPARATOR = "__,__"
        const val FIRST_VAL_SEPARATOR = "__:__"
        const val SECOND_VAL_SEPARATOR = "__;__"
        const val THIRD_VAL_SEPARATOR = "__~__"
        const val FOURTH_VAL_SEPARATOR = "__|__"
    }
}
