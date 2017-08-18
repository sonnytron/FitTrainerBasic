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
    }
}

class KeyConstants {
    companion object {
        const val KEY_RESULT_TEXT = "keyResultText"
        const val KEY_RESULT_KEY_LONG = "keyResultKeyLong"
        const val KEY_RESULT_LONG = "keyResultLong"
        const val KEY_RESULT_SET = "keyResultSet"
        const val KEY_RESULT_BOOLEAN = "keyResultBoolean"
    }
}

class ConverterConstants {
    companion object {
        const val OBJECT_SEPARATOR = "__,__"
        const val FIRST_VAL_SEPARATOR = "__:__"
        const val SECOND_VAL_SEPARATOR = "__;__"
        const val THIRD_VAL_SEPARATOR = "__~__"
    }
}
