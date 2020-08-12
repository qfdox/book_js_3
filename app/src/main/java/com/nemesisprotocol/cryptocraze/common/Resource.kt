package com.nemesisprotocol.cryptocraze.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<