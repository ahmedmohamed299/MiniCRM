package com.ahmed.minicrm.presentation.ui.customers

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.minicrm.data.model.CustomerModel
import com.ahmed.minicrm.databinding.CustomerItemBinding


class CustomerAdapter : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<CustomerModel>() {
        override fun areItemsTheSame(oldItem: CustomerModel, newItem: CustomerModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CustomerModel, newItem: CustomerModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    class CustomerViewHolder(private val binding: CustomerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(customerModel: CustomerModel) {
            binding.customer = customerModel

            binding.call.setOnClickListener {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + customerModel.number))


                binding.root.context.startActivity(intent)

            }


            binding.container.setOnClickListener {
                val action = CustomersFragmentDirections.actionFirstFragmentToSecondFragment(customerModel.id!!)
                binding.root.findNavController().navigate(action)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = CustomerItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customerModel = differ.currentList[position]
        holder.bind(customerModel)
    }

    override fun getItemCount(): Int = differ.currentList.size
}