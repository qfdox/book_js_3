package com.nemesisprotocol.cryptocraze.data.database.converters

import androidx.room.TypeConverter

class ListTypeConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun gettingFloatListFromString(floatList: String?): List<Float> {
            val list = arrayListOf<Float>()

            val array =
                floatList?.split(",".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
            if (array.isNullOrEmpty()) {
                return emptyList()
            }
            for (s in array) {
                if (s.isNotEmpty()) {
                    list.add(s.toFloat())
                }
            }
            return list