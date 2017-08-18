package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.sonnyrodriguez.fittrainer.fittrainerbasic.values.ConverterConstants

class Converters {

    @TypeConverter
    fun convertMapToString(longMap: LinkedHashMap<Long, Long>): String {
        val longSeparator = "_:_"
        val valueSeparator = "__,__"
        val gson = Gson()
        val entrySet = longMap.entries
        var convertedString = ""
        entrySet.forEachIndexed { index, mutableEntry ->
            val insideKey = gson.toJson(mutableEntry.key)
            val insideValue = gson.toJson(mutableEntry.value)
            val stringToAdd = if (index == longMap.size - 1)
                "$insideKey$longSeparator$insideValue" else
                "$insideKey$longSeparator$insideValue$valueSeparator"
            convertedString = "$convertedString$stringToAdd"
        }
        return convertedString
    }

    @TypeConverter
    fun convertStringToMap(gsonString: String): LinkedHashMap<Long, Long> {
        val longSeparator = "_:_"
        val valueSeparator = "__,__"
        val returnMap = LinkedHashMap<Long, Long>()
        val gson = Gson()
        val valueArray = gsonString.split(valueSeparator)
        valueArray.forEach { entryString ->
            val entryList = entryString.split(longSeparator)
            if (entryList.size == 2) {
                val insideKey = gson.fromJson(entryList[0], Long::class.java)
                val insideValue = gson.fromJson(entryList[1], Long::class.java)
                returnMap[insideKey] = insideValue
            }
        }
        return returnMap
    }

    @TypeConverter
    fun convertToString(longList: List<Long>): String {
        val stringSeparator = "__,__"
        var convertedString = ""
        val gson = Gson()
        longList.forEachIndexed { index, longValue ->
            gson.toJson(longValue).let {
                val stringToAdd = if (index == longList.count() - 1) it else "$it$stringSeparator"
                convertedString = "$convertedString$stringToAdd"
            }
        }
        return convertedString
    }

    @TypeConverter
    fun convertToList(longString: String): List<Long> {
        val stringSeparator = "__,__"
        val returnList = ArrayList<Long>()
        val gson = Gson()
        val stringArray = longString.split(stringSeparator)
        stringArray.forEach {
            returnList.add(gson.fromJson(it, Long::class.java))
        }
        return returnList
    }

    @TypeConverter
    fun convertStringToExerciseList(gsonString: String): List<LocalExerciseObject> {
        val returnList = ArrayList<LocalExerciseObject>()
        val gson = Gson()
        val objectStringArray = gsonString.split(ConverterConstants.OBJECT_SEPARATOR)
        objectStringArray.forEach { totalObject ->
            val firstValArray = totalObject.split(ConverterConstants.FIRST_VAL_SEPARATOR)
            val title = firstValArray[0]
            val secondValArray = firstValArray[1].split(ConverterConstants.SECOND_VAL_SEPARATOR)
            val count = secondValArray[0]
            val thirdValArray = secondValArray[1].split(ConverterConstants.THIRD_VAL_SEPARATOR)
            val set = thirdValArray[0]
            val exerciseId = thirdValArray[1]
            returnList.add(LocalExerciseObject(title, count.toLong(), set.toLong(), exerciseId.toLong()))
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
            val title = localExerciseObject.title
            val objectString = "$title${ConverterConstants.FIRST_VAL_SEPARATOR}$countGson${ConverterConstants.SECOND_VAL_SEPARATOR}$setGson${ConverterConstants.THIRD_VAL_SEPARATOR}$idGson"
            val stringToAdd = if (index == exerciseList.count() - 1) objectString else "$objectString${ConverterConstants.OBJECT_SEPARATOR}"
            convertedString = "$convertedString$stringToAdd"
        }
        return convertedString
    }

}
