package com.ahmed.minicrm.presentation.di

import com.ahmed.minicrm.domain.repository.CustomerRepository
import com.ahmed.minicrm.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetAllCustomersFromDataBaseUseCase(customerRepository: CustomerRepository)
    : GetAllCustomersFromDataBaseUseCase {
        return GetAllCustomersFromDataBaseUseCase(customerRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteByIdCustomerFromDataBaseUseCase(customerRepository: CustomerRepository)
    : DeleteByIdCustomerFromDataBaseUseCase {
        return DeleteByIdCustomerFromDataBaseUseCase(customerRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteAllCustomersFromDataBaseUseCase(customerRepository: CustomerRepository)
    : DeleteAllCustomersFromDataBaseUseCase {
        return DeleteAllCustomersFromDataBaseUseCase(customerRepository)
    }

    @Singleton
    @Provides
    fun provideInsertCustomerToDataBaseUseCase(customerRepository: CustomerRepository)
    : InsertCustomerToDataBaseUseCase {
        return InsertCustomerToDataBaseUseCase(customerRepository)
    }

    @Singleton
    @Provides
    fun provideGetUpdateCustomerFromDataBaseUseCase(customerRepository: CustomerRepository)
    : UpdateCustomerFromDataBaseUseCase {
        return UpdateCustomerFromDataBaseUseCase(customerRepository)
    }
    @Singleton
    @Provides
    fun provideGetCustomerByIdFromDbUseCase(customerRepository: CustomerRepository)
    : GetCustomerByIdFromDbUseCase {
        return GetCustomerByIdFromDbUseCase(customerRepository)
    }
}