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
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _transactionHistory = MutableStateFlow<List<TransactionRecord>>(emptyList())
    val transactionHistory: StateFlow<List<TransactionRecord>> = _transactionHistory

    init {
        viewModelScope.launch(dispatcherProvider.io) {
            _transactionHistory.value = getTransactionRecordsUseCase()
        }
    }

    fun addTransactionRecord(transactionRecord: TransactionRecord) {
        viewModelScope.launch(dispatcherProvider.io) {
            addTransactionRecordUseCase(transactionRecord = transactionRecord)
        }
    }

    fun deleteTransactionRecord(transactionRecord: TransactionRecord) {
        viewModelScope.launch(dispatche