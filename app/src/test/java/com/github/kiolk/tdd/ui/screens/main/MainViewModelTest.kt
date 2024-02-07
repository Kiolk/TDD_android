package com.github.kiolk.tdd.ui.screens.main

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class MainViewModelTest {

    @Test
    fun `check viewModel exist`() {
        val viewModel = MainViewModel()

        assertNotNull(viewModel)
    }

    @Test
    fun `when value input then inputted value display`() {
        val viewModel = MainViewModel()

        viewModel.onInputChanges("23")

        assertEquals("23", viewModel.mainUiState.value.inputAmount)
    }

    @Test
    fun `when start then show default tip in 10 percents`() {
        val viewModel = MainViewModel()

        assertEquals(10f, viewModel.mainUiState.value.tip)
    }

    @Test
    fun `when change seek bar than value of tip changed`() {
        val viewModel = MainViewModel()

        viewModel.onTipValueChanged(40)

        assertEquals(40f, viewModel.mainUiState.value.tip)
    }

    @Test
    fun `when input value than calculate amount with tip but without VAT`() {
        val viewModel = MainViewModel()

        viewModel.onInputChanges("100")
        viewModel.onTipValueChanged(30)

        assertEquals(130f, viewModel.mainUiState.value.amountWithTip)
    }

    @Test
    fun `when input value then calculate total amount with VAT`() {
        val viewModel = MainViewModel()

        viewModel.onInputChanges("100")
        viewModel.onTipValueChanged(30)

        assertEquals(162.5f, viewModel.mainUiState.value.totalAmount)
    }
}
