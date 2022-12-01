package com.ahmed.minicrm.presentation.di

import android.app.Application
import androidx.room.Room
import com.ahmed.minicrm.data.database.CustomerDao
import com.ahmed.minicrm.data.database.CustomerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNewsDatabase(app: Application): CustomerDatabase {
        return Room.databaseBuilder(app, CustomerDatabase::class.java, "customer_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(database: CustomerDatabase): CustomerDao {
        return database.getCustomers()
    }


}