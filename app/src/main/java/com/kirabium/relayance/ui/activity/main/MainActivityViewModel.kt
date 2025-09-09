package com.kirabium.relayance.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirabium.relayance.domain.model.Customer
import com.kirabium.relayance.repository.CustomerRepository

class CustomerListViewModel(
    private val repository: CustomerRepository = CustomerRepository()
) : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>> get() = _customers

    init {
        loadCustomers()
    }

    private fun loadCustomers() {
        _customers.value = repository.getCustomers()
    }
}