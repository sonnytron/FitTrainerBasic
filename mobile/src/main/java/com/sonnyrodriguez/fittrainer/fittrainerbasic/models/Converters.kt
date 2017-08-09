package com.sonnyrodriguez.fittrainer.fittrainerbasic.models

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

class Converters {

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
