package com.kirabium.relayance.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kirabium.relayance.databinding.CustomerItemBinding
import com.kirabium.relayance.domain.model.Customer

class CustomerAdapter(
    private val customers: List<Customer>,
    private val onClick: (Customer) -> Unit
) : ListAdapter<Customer, CustomerAdapter.CustomerViewHolder>(CustomerDiffCallback()) {

    class CustomerViewHolder(
        private val binding: CustomerItemBinding,
        val onClick: (Customer) -> Unit) : RecyclerView.ViewHolder(binding.root
        ) {
        private var currentCustomer: Customer? = null

        init {
            binding.root.setOnClickListener {
                currentCustomer?.let {
                    onClick(it)
                }
            }
        }

        fun bind(customer: Customer) {
            currentCustomer = customer
            with(binding) {
                nameTextView.text = customer.name
                emailTextView.text = customer.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = CustomerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customers[position]
        holder.bind(customer)
    }

    class CustomerDiffCallback : DiffUtil.ItemCallback<Customer>() {
        override fun areItemsTheSame(oldItem: Customer, newItem: Customer): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Customer, newItem: Customer): Boolean =
            oldItem == newItem
    }

    override fun getItemCount() = customers.size
}
