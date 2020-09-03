package com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto

import com.google.gson.annotations.SerializedName
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.Coin

data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedNa