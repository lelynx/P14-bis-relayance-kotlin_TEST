package com.kirabium.relayance.ui.activity.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kirabium.relayance.databinding.ActivityMainBinding
import com.kirabium.relayance.ui.activity.addCustomer.AddCustomerActivity
import com.kirabium.relayance.ui.activity.detail.DetailActivity
import com.kirabium.relayance.ui.adapter.CustomerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var customerAdapter: CustomerAdapter
    private val viewModel: CustomerListViewModel by viewModels()

    // moi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupCustomerRecyclerView()
        setupFab()
        observeViewModel()
    }

    // TODO: onResume pour rafraichir le recyclerview, après add customer
    override fun onResume() {
        super.onResume()
        viewModel.loadCustomers()
        viewModel.customers.observe(this) { customers ->
            println("DEBUG MainActivity#onResume -> ${customers.size} clients")
            customerAdapter.submitList(customers)
        }
    }

    private fun setupFab() {
        binding.addCustomerFab.setOnClickListener {
            val intent = Intent(this, AddCustomerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupCustomerRecyclerView() {
        binding.customerRecyclerView.layoutManager = LinearLayoutManager(this)
        customerAdapter = CustomerAdapter() { customer ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_CUSTOMER_ID, customer.id)
            }
            startActivity(intent)
        }
        binding.customerRecyclerView.adapter = customerAdapter
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun observeViewModel() {
        // TODO:
        viewModel.customers.observe(this) { customers ->
            println("DEBUG ViewModel#observeViewModel -> ${customers.size} clients")
            customerAdapter.submitList(customers)
        }
    }
}
