package com.ahmed.minicrm.presentation.di

import com.ahmed.minicrm.data.database.CustomerDao
import com.ahmed.minicrm.data.repository.datasource.CustomerLocalDataSource
import com.ahmed.minicrm.data.repository.datasourceImpl.CustomerLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideCustomersLocalDataSource(customerDao: CustomerDao): CustomerLocalDataSource {
        return CustomerLocalDataSourceImpl(customerDao)
    }
}