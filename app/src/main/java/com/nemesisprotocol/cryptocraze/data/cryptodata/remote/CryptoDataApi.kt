
/*
* MIT License
*
* Copyright (c) 2020 Gurupreet Singh
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
 */

package com.nemesisprotocol.cryptocraze.data.cryptodata.remote

import com.nemesisprotocol.cryptocraze.data.cryptodata.remote.dto.CryptoDataApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoDataApi {

    @GET("coins/markets?vs_currency=gbp&order=market_cap_desc&sparkline=true")
    suspend fun getAllCrypto(
        @Query("page") page: Int = 1,
        @Query("per_page") pageSize: Int = 20
    ): Response<List<CryptoDataApiResponse>>

    @GET("coins/markets?vs_currency=gbp&order=market_cap_desc&sparkline=false")
    suspend fun getCryptoDataBySymbol(
        @Query("symbols") symbol: String,
    ): Response<List<CryptoDataApiResponse>>
}