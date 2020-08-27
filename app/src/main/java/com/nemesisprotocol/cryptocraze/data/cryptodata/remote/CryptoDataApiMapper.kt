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
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData

class CryptoDataApiMapper {
    fun map(cryptoApiResponse: CryptoDataApiResponse) = CryptoData(
        name = cryptoApiResponse.name,
        symbol = cryptoApiResponse.symbol,
        price = cryptoApiResponse.current_price,
        image = cryptoApiResponse.image,
        dailyChange = cryptoApiResponse.price_change_24h,
        dailyChangePercentage = cryptoApiResponse.price_change_percentage_24h,
        high = cryptoApiResponse.high_24h,
        low = cryptoApiResponse.low_24h,
        volume = cryptoApiResponse.total_volume,
        supply = cryptoApiResponse.total_supply,
        marketCap = cryptoApiRe