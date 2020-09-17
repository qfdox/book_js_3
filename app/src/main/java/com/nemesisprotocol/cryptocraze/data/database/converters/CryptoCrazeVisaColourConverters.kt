package com.nemesisprotocol.cryptocraze.data.database.converters

import androidx.room.TypeConverter
import com.nemesisprotocol.cryptocraze.presentation.walletscreen.CryptoCrazeVisaColour

class CryptoCrazeVisaColourConverters {

    @TypeConverter
    fun toCryptoCrazeVisaColour(value: String) = enumValueOf<CryptoCrazeVisaColour>(value)

    @TypeConverter
    fun fromCryptoCrazeVisaColour(cryp