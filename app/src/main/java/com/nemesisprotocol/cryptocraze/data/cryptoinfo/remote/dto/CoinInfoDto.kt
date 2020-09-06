package com.nemesisprotocol.cryptocraze.data.cryptoinfo.remote.dto

import com.google.gson.annotations.SerializedName
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.CoinDetail

data class CoinInfoDto(
    val description: String,
    @SerializedName("development_status")
    val developmentStatus: String,
    @SerializedName("first_data_at")
    val firstDataAt: String,
    @SerializedName("hardware_wallet")
    