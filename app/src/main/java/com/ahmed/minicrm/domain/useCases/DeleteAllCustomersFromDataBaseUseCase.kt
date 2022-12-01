package com.ahmed.minicrm.domain.useCases

import com.ahmed.minicrm.domain.repository.CustomerRepository

class DeleteAllCustomersFromDataBaseUseCase(private val customerRepository: CustomerRepository) {
    suspend fun execute(): Int = customerRepository.deleteAllCustomer()
}