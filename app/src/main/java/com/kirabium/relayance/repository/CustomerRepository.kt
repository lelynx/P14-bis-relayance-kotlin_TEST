package com.kirabium.relayance.repository

import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.domain.model.Customer
import java.util.concurrent.atomic.AtomicInteger

class CustomerRepository {

    private val idGenerator = AtomicInteger(DummyData.customers.maxOfOrNull { it.id } ?: 0)
    private val customersInternal = DummyData.customers.toMutableList()

    fun getCustomers(): List<Customer> {
        return customersInternal // retourne la liste dynamique
    }

    fun getCustomerById(id: Int): Customer? {
        return customersInternal.find { it.id == id }
    }

    fun addCustomer(customer: Customer): Customer {
        val toAdd = if (customer.id <= 0) {
            customer.copy(id = idGenerator.incrementAndGet())
        } else customer
        customersInternal.add(toAdd)
        return toAdd
    }

    fun clearAll() {
        customersInternal.clear()
    }
}
