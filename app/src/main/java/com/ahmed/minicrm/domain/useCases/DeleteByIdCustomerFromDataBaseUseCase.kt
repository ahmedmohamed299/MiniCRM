package com.ahmed.minicrm.domain.useCases

import com.ahmed.minicrm.domain.repository.CustomerRepository

class DeleteByIdCustomerFromDataBaseUseCase(private val customerRepository: CustomerRepository) {
    suspend fun execute(id: Int): Int = customerRepository.deleteByName(id)
}