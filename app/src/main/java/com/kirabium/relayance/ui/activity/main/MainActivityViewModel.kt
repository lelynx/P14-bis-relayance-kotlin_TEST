package com.kirabium.relayance.ui.activity.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kirabium.relayance.domain.model.Customer
import com.kirabium.relayance.repository.CustomerRepository

class CustomerListViewModel(
    // TODO: Ici, on référence l'Object repository au lieu de créer une instance
    private val repository: CustomerRepository = CustomerRepository
) : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>> get() = _customers

    init {
        loadCustomers()
    }

    fun loadCustomers() {
        // affiche le hash code de repository
        println("DEBUG CustomerListViewModel#loadCustomers -> ${repository.hashCode()}")
        _customers.value = repository.getCustomers().toList()
    }

}