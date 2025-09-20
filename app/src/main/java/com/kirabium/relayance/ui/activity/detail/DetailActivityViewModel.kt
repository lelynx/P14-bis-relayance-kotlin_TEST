package com.kirabium.relayance.ui.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirabium.relayance.domain.model.Customer
import com.kirabium.relayance.repository.CustomerRepository

class DetailActivityViewModel(
    private val repository: CustomerRepository = CustomerRepository
) : ViewModel() {

    private val _customer = MutableLiveData<Customer?>()
    val customer: LiveData<Customer?> get() = _customer

    fun loadCustomer(id: Int) {
        _customer.value = repository.getCustomerById(id)
    }
}