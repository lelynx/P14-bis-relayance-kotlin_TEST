package com.kirabium.relayance.ui.activity.addCustomer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kirabium.relayance.ui.composable.AddCustomerScreen

class AddCustomerActivity : AppCompatActivity() {

    private val viewModel: AddCustomerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            AddCustomerScreen(
                uiState = uiState,
                onNameChanged = viewModel::onNameChanged,
                onEmailChanged = viewModel::onEmailChanged,
                onSave = viewModel::saveCustomer,
                onBack = { onBackPressedDispatcher.onBackPressed() }
            )
        }
    }
}