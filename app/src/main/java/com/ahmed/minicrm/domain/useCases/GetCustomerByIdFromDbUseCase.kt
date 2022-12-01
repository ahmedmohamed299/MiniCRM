package com.ahmed.minicrm.domain.useCases

import com.ahmed.minicrm.data.model.CustomerModel
import com.ahmed.minicrm.domain.repository.CustomerRepository

class GetCustomerByIdFromDbUseCase(private val repository: CustomerRepository) {
    suspend fun execute(id:Int)=repository.getCustomerById(id)
}