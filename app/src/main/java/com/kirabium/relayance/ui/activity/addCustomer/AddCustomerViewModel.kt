package com.kirabium.relayance.ui.activity.addCustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kirabium.relayance.domain.model.Customer
import com.kirabium.relayance.repository.CustomerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.regex.Pattern

data class AddCustomerUiState(
    val name: String = "",
    val email: String = "",
    val isNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val success: Boolean = false,
    val errorMessage: String? = null
)

class AddCustomerViewModel(
    private val repository: CustomerRepository = CustomerRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddCustomerUiState())
    val uiState: StateFlow<AddCustomerUiState> = _uiState

    fun onNameChanged(newName: String) {
        _uiState.value = _uiState.value.copy(name = newName, isNameValid = newName.isNotBlank())
    }

    fun onEmailChanged(newEmail: String) {
        val isValid = EMAIL_PATTERN.matcher(newEmail).matches()
        _uiState.value = _uiState.value.copy(email = newEmail, isEmailValid = isValid)
    }

    fun saveCustomer() {
        val state = _uiState.value
        if (state.name.isBlank()) {
            _uiState.value = state.copy(isNameValid = false, errorMessage = "Le nom est obligatoire")
            return
        }
        if (!EMAIL_PATTERN.matcher(state.email).matches()) {
            _uiState.value = state.copy(isEmailValid = false, errorMessage = "Email invalide")
            return
        }

        viewModelScope.launch {
            repository.addCustomer(
                Customer(
                    id = 0,
                    name = state.name,
                    email = state.email,
                    createdAt = Date()
                )
            )
            _uiState.value = state.copy(success = true, errorMessage = null)
        }
    }

    companion object {
        private val EMAIL_PATTERN: Pattern =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    }
}