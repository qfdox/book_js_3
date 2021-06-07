package com.nemesisprotocol.cryptocraze.presentation.cryptotransactionscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nemesisprotocol.cryptocraze.common.DispatcherProvider
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.TransactionRecord
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.usecase.AddTransactionRecordUseCase
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.usecase.DeleteTransactionRecordUseCase
import com.nemesisprotocol.cryptocraze.domain.transactionhistory.usecase