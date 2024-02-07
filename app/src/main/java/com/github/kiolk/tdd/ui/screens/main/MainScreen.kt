package com.github.kiolk.tdd.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.kiolk.tdd.ui.theme.TDDTheme

@Composable
fun MainScreen() {


    val mainViewModel: MainViewModel = viewModel()
    val mainUiState by mainViewModel.mainUiState.collectAsState()

    MainScreenRenderer(mainUiState, onAmountValueChanged = {
        mainViewModel.onInputChanges(it)
    }, onTipValueChanged = {
        mainViewModel.onTipValueChanged(it.toInt())
    })
}

@Composable
fun MainScreenRenderer(
    viewModel: MainUiState,
    onAmountValueChanged: (newValue: String) -> Unit = {},
    onTipValueChanged: (newValue: Float) -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,
    ) {
        BillInputTitle()
        Spacer(modifier = Modifier.height(20.dp))
        BillInput(viewModel.inputAmount, onAmountValueChanged)
        Spacer(modifier = Modifier.height(20.dp))
        TipSuggestion(viewModel.tip)
        Spacer(modifier = Modifier.height(20.dp))
        TipChangeBar(viewModel.tip, onTipValueChanged)
        Spacer(modifier = Modifier.height(20.dp))
        AmountWithTip(viewModel.amountWithTip)
        Spacer(modifier = Modifier.height(20.dp))
        TotalAmount(viewModel.totalAmount)
    }
}

@Composable
fun BillInputTitle() {
    Text(text = "Enter Bill Amount", style = MaterialTheme.typography.displaySmall)
}

@Composable
fun BillInput(input: String = "", onValueChanged: (newValue: String) -> Unit) {
    OutlinedTextField(
        value = input,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        onValueChange = onValueChanged,
        textStyle = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
    )
}

@Composable
fun TipSuggestion(hint: Float = 0f) {
    Text(text = "Tip: $hint%", style = MaterialTheme.typography.bodySmall)
}

@Composable
fun TipChangeBar(value: Float = 0f, onValueChanged: (newValue: Float) -> Unit) {
    Slider(
        value = value, onValueChange = onValueChanged,
        valueRange = 0f..100f
    )
}

@Composable
fun AmountWithTip(amountWithTip: Float = 0f) {
    Text(text = "$amountWithTip Kr")
    Text(text = "Amount(ex.VAT)", style = MaterialTheme.typography.bodySmall)
}

@Composable
fun TotalAmount(totalAmount: Float = 0f) {
    Text(text = "Total Amount", style = MaterialTheme.typography.titleMedium)
    Text(text = "$totalAmount Kr")
    Text(text = "VAT included(25%)", style = MaterialTheme.typography.bodySmall)
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenDarkPreview(@PreviewParameter(provider = MainViewModelPreviewParameterProvider::class) viewModel: MainUiState) {
    TDDTheme(darkTheme = true) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            MainScreenRenderer(viewModel)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainScreenLightPreview(@PreviewParameter(provider = MainViewModelPreviewParameterProvider::class) viewModel: MainUiState) {
    TDDTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            MainScreenRenderer(viewModel)
        }
    }
}

class MainViewModelPreviewParameterProvider : PreviewParameterProvider<MainUiState> {

    override val values: Sequence<MainUiState> = sequenceOf(
        MainUiState(
            inputAmount = "100",
            tip = 10f,
            totalAmount = 137.5f
        )
    )
}
