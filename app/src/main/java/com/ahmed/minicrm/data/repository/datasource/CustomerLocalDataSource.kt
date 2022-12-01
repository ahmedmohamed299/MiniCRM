package com.ahmed.minicrm.data.repository.datasource

import com.ahmed.minicrm.data.model.CustomerModel
import kotlinx.coroutines.flow.Flow

interface CustomerLocalDataSource {
    suspend fun insertCustomer(customerModel: CustomerModel)
    suspend fun getAllCustomers():Flow<List<CustomerModel>>
    suspend fun updateCustomer(customerModel: CustomerModel):Int
    suspend fun deleteAllCustomer():Int
    suspend fun deleteByName(id:Int):Int
    suspend fun getCustomerById(id:Int):CustomerModel

}