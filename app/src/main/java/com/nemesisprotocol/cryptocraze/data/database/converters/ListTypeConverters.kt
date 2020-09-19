package com.nemesisprotocol.cryptocraze.data.database.converters

import androidx.room.TypeConverter

class ListTypeConverters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun gettingFloatListFromString(floatList: String?): List<Float> {
            val lis