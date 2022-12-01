package com.ahmed.minicrm.data.repository.datasourceImpl

import com.ahmed.minicrm.data.database.CustomerDao
import com.ahmed.minicrm.data.model.CustomerModel
import com.ahmed.minicrm.data.repository.datasource.CustomerLocalDataSource
import kotlinx.coroutines.flow.Flow

class CustomerLocalDataSourceImpl(private val customerDao: CustomerDao) : CustomerLocalDataSource {
    override suspend fun insertCustomer(customerModel: CustomerModel) =
        customerDao.insertCustomer(customerModel)

    override suspend fun getAllCustomers(): Flow<List<CustomerModel>> =
        customerDao.getAllCustomers()

    override suspend fun updateCustomer(customerModel: CustomerModel): Int =
        customerDao.updateCustomer(customerModel)

    override suspend fun deleteAllCustomer(): Int = customerDao.deleteCustomer()

    override suspend fun deleteByName(id: Int): Int = customerDao.deleteByName(id)
    override suspend fun getCustomerById(id: Int): CustomerModel = customerDao.getCustomerById(id)
}