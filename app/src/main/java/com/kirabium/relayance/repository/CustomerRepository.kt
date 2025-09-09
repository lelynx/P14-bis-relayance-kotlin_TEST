package com.kirabium.relayance.repository

import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.domain.model.Customer

class CustomerRepository {

    fun getCustomers(): List<Customer> {
        return DummyData.customers
    }

    fun getCustomerById(id: Int): Customer? {
        return DummyData.customers.find { it.id == id }
    }
}
