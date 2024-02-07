package com.github.kiolk.tdd.ui.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState: StateFlow<MainUiState> = _mainUiState.asStateFlow()

    fun onInputChanges(inputValue: String) {
        _mainUiState.value = _mainUiState.value.copy(inputAmount = inputValue)
        calculateAmountWithTip()
    }

    fun onTipValueChanged(tipValue: Int) {
        _mainUiState.value = _mainUiState.value.copy(tip = tipValue.toFloat())
        calculateAmountWithTip()
    }

    private fun calculateAmountWithTip() {
        val tip = _mainUiState.value.tip
        val amount = _mainUiState.value.inputAmount.toFloatOrNull() ?: 0f
        val amountWithTip = amount + ((tip * amount) / 100)
        _mainUiState.value = _mainUiState.value.copy(amountWithTip = amountWithTip, totalAmount = amountWithTip + (amountWithTip * 25) / 100)
    }
}

data class MainUiState(
    val tip: Float = 10f,
    val inputAmount: String = "",
    val amountWithTip: Float = 0f,
    val totalAmount: Float = 0f,
)
