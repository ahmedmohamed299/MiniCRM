package com.ahmed.minicrm.data.repository

import com.ahmed.minicrm.data.model.CustomerModel
import com.ahmed.minicrm.data.repository.datasource.CustomerLocalDataSource
import com.ahmed.minicrm.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow


class CustomerRepositoryImpl
    (private val customerLocalDataSource: CustomerLocalDataSource) : CustomerRepository {


    override suspend fun insertCustomer(customerModel: CustomerModel) =
        customerLocalDataSource.insertCustomer(customerModel)


    override suspend fun getAllCustomers(): Flow<List<CustomerModel>> =
        customerLocalDataSource.getAllCustomers()

    override suspend fun updateCustomer(customerModel: CustomerModel): Int =
        customerLocalDataSource.updateCustomer(customerModel)


    override suspend fun deleteAllCustomer(): Int = customerLocalDataSource.deleteAllCustomer()

    override suspend fun deleteByName(id: Int): Int = customerLocalDataSource.deleteByName(id)
    override suspend fun getCustomerById(id: Int): CustomerModel =
        customerLocalDataSource.getCustomerById(id)
}


