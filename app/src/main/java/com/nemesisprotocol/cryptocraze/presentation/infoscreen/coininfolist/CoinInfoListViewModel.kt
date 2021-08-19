package com.nemesisprotocol.cryptocraze.presentation.infoscreen.coininfolist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesisprotocol.cryptocraze.common.Resource
import com.nemesisprotocol.cryptocraze.domain.cryptoinfo.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinInfoListViewModel @Inject constructor(
    p