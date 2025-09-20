package com.kirabium.relayance.repository

import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.domain.model.Customer
import java.util.concurrent.atomic.AtomicInteger

// TODO: CustomerRepository est maitenant on Object (Singleton),
//  pour garantir qu'il n'y ai qu'une seule instance
object CustomerRepository {

    private val idGenerator = AtomicInteger(DummyData.customers.maxOfOrNull { it.id } ?: 0)
    private val customersInternal = DummyData.customers.toMutableList()

    fun getCustomers(): List<Customer> {
        println("DEBUG getCustomers() -> ${customersInternal.size} clients")
        println("DEBUG getCustomers() -> hashCode: ${this.hashCode()}")
        return customersInternal // retourne la liste dynamique
    }

    fun getCustomerById(id: Int): Customer? {
        return customersInternal.find { it.id == id }
    }

    fun addCustomer(customer: Customer): Customer {
        val toAdd = if (customer.id <= 0) {
            customer.copy(id = idGenerator.incrementAndGet())
        } else customer
        println("DEBUG addCustomer() -> hashCode: ${this.hashCode()}")
        customersInternal.add(toAdd)
        println("DEBUG addCustomer() -> ${customersInternal.size} clients")

        return toAdd
    }

    fun clearAll() {
        customersInternal.clear()
    }
}
