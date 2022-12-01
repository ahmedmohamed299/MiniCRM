package com.ahmed.minicrm.presentation.di

import com.ahmed.minicrm.data.repository.CustomerRepositoryImpl
import com.ahmed.minicrm.data.repository.datasource.CustomerLocalDataSource
import com.ahmed.minicrm.domain.repository.CustomerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideHistoricalRepository(
        customerLocalDataSource: CustomerLocalDataSource,
    ): CustomerRepository {
        return CustomerRepositoryImpl(customerLocalDataSource)
    }

}