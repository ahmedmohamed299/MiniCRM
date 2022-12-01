package com.ahmed.minicrm.domain.useCases

import com.ahmed.minicrm.data.model.CustomerModel
import com.ahmed.minicrm.domain.repository.CustomerRepository

class InsertCustomerToDataBaseUseCase(private val customerRepository: CustomerRepository) {
    suspend fun execute(customerModel: CustomerModel) =customerRepository.insertCustomer(customerModel)


}