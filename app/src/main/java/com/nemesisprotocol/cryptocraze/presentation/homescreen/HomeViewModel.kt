package com.nemesisprotocol.cryptocraze.presentation.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.data.paging.PageNumberSource
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoData
import com.nemesisprotocol.cryptocraze.domain.cryptodata.CryptoDataRepo
import com.nemesisprotocol.cryptocraze.domain.cryptodata.usecase.AddFavCryptoDataUseCase
import com.nemesisprotocol.cryptocraze.domain.cryptodata.usecase.CheckFavCryptoExistsUseCase
import com.nemesisprotocol.cryptocraze.domain.cryptodata.usecase.GetFavCryptosDataUseCase
import com.nemesisprotocol.cryptocraze.domain.cryptodata.usecase.RemoveFavCryptoDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFavCryptoDataUseCase: GetFavCryptosDataUseCase,
    private val addFavCryptoDataUseCase: AddFavCryptoDataUseCase,
    private val removeFavCryp