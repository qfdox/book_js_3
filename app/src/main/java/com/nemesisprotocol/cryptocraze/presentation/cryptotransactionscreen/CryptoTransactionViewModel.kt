package com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.usecase.AddTransactionRecordUseCase
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.usecase.DeleteTransactionRecordUseCase
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.usecase.GetTransactionRecordsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoTransactionViewModel @Inject constructor(
    private val addTransactionRecordUseCase: AddTransactionRecordUseCase,
    private val deleteTransactionRecordUseCase: DeleteTransactionRecordUseCase,
    private val getTransactionRecordsUseCase: GetTransactionRecordsUseCase,
    private val dispatcherProvider: Dispatche