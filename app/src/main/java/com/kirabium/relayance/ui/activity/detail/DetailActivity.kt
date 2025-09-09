package com.kirabium.relayance.ui.activity.detail

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kirabium.relayance.data.DummyData
import com.kirabium.relayance.ui.composable.DetailScreen

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CUSTOMER_ID = "customer_id"
    }

    private val viewModel: DetailActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val customerId = intent.getIntExtra(EXTRA_CUSTOMER_ID, -1)
        viewModel.loadCustomer(customerId)

        setContent {
            val customer = viewModel.customer.value
            customer?.let {
                DetailScreen(customer = it) {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }
}


