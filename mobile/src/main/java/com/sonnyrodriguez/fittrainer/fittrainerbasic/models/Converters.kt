package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.ConverterConstants

class Converters {

    @TypeConverter
    fun convertStringToExerciseList(gsonString: String): List<LocalExerciseObject> {
        val returnList = ArrayList<LocalExerciseObject>()
        val gson = Gson()
        val objectStringArray = gsonString.split(ConverterConstants.OBJECT_SEPARATOR)
        objectStringArray.forEach { totalObject ->
            val firstValArray = totalObject.split(ConverterConstants.FIRST_VAL_SEPARATOR)
            val title = gson.fromJson(firstValArray[0], String::class.java)
            val secondValArray = firstValArray[1].split(ConverterConstants.SECOND_VAL_SEPARATOR)
            val count = gson.fromJson(secondValArray[0], Long::class.java)
            val thirdValArray = secondValArray[1].split(ConverterConstants.THIRD_VAL_SEPARATOR)
            val set = gson.fromJson(thirdValArray[0], Long::class.java)
            val fourthValArray = thirdValArray[1].split(ConverterConstants.FOURTH_VAL_SEPARATOR)
            val exerciseId = gson.fromJson(fourthValArray[0], Long::class.java)
            val muscleInt = gson.fromJson(fourthValArray[1], Int::class.java)
            returnList.add(LocalExerciseObject(title, count, set, exerciseId, muscleInt))
        }
        return returnList
    }

    @TypeConverter
    fun convertExerciseListToString(exerciseList: List<LocalExerciseObject>): String {
        var convertedString = ""
        val gson = Gson()
        exerciseList.forEachIndexed { index, localExerciseObject ->
            val countGson = gson.toJson(localExerciseObject.count)
            val setGson = gson.toJson(localExerciseObject.set)
            val idGson = gson.toJson(localExerciseObject.exerciseId)
            val title = gson.toJson(localExerciseObject.title)
            val muscle = gson.toJson(localExerciseObject.muscleGroup)
            val objectString = "$title${ConverterConstants.FIRST_VAL_SEPARATOR}$countGson${ConverterConstants.SECOND_VAL_SEPARATOR}$setGson${ConverterConstants.THIRD_VAL_SEPARATOR}$idGson${ConverterConstants.FOURTH_VAL_SEPARATOR}$muscle"
            val stringToAdd = if (index == exerciseList.count() - 1) objectString else "$objectString${ConverterConstants.OBJECT_SEPARATOR}"
            convertedString = "$convertedString$stringToAdd"
        }
        return convertedString
    }

    @TypeConverter
    fun convertStringToStringList(gsonString: String): List<String> {
        val returnList = ArrayList<String>()
        val gson = Gson()
        val objectStringArray = gsonString.split(ConverterConstants.OBJECT_SEPARATOR)
        objectStringArray.forEach { internalString ->
            returnList.add(gson.fromJson(internalString, String::class.java))
        }
        return returnList
    }

    @TypeConverter
    fun convertStringListToString(stringList: List<String>): String {
        var convertedString = ""
        val gson = Gson()
        stringList.forEachIndexed { index, stringValue ->
            val stringToAdd = if (index == stringList.count() - 1)
                gson.toJson(stringValue) else
                "${gson.toJson(stringValue)}${ConverterConstants.OBJECT_SEPARATOR}"
            convertedString = "$convertedString$stringToAdd"
        }
        return convertedString
    }

    @TypeConverter
    fun convertStringToLongList(gsonString: String): List<Long> {
        val returnList = ArrayList<Long>()
        val gson = Gson()
        gsonString.split(ConverterConstants.OBJECT_SEPARATOR).forEach { internalString ->
            returnList.add(gson.fromJson(internalString, Long::class.java))
        }
        return returnList
    }

    @TypeConverter
    fun convertLongListToString(longList: List<Long>): String {
        var convertedString = ""
        val gson = Gson()
        longList.forEachIndexed { index, longValue ->
            val stringToAdd = if (index == longList.count() - 1)
                gson.toJson(longValue) else
                "${gson.toJson(longValue)}${ConverterConstants.OBJECT_SEPARATOR}"
            convertedString = "$convertedString$stringToAdd"
        }
        return convertedString
    }
}
