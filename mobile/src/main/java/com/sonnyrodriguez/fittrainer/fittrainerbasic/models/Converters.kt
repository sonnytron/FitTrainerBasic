package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

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
}
