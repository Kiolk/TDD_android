package com.github.kiolk.tdd.ui.screens.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _mainUiState = MutableStateFlow(MainUiState())
    val mainUiState: StateFlow<MainUiState> = _mainUiState.asStateFlow()

    fun onInputChanges(inputValue: String) {
        //TODO need implement
    }

    fun onTipValueChanged(tipValue: Int) {
        //TODO need implement
    }

}

data class MainUiState(
    val tip: Float = 0f,
    val inputAmount: String = "",
    val amountWithTip: Float = 0f,
    val totalAmount: Float = 0f,
)
