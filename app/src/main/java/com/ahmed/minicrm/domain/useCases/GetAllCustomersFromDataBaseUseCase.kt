package com.ahmed.minicrm.domain.useCases

import com.ahmed.minicrm.data.model.CustomerModel
import com.ahmed.minicrm.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetAllCustomersFromDataBaseUseCase(private val customerRepository: CustomerRepository) {
    suspend fun execute():Flow<List<CustomerModel>> = customerRepository.getAllCustomers()
}