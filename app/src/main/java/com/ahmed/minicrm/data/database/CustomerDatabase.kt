package com.ahmed.minicrm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmed.minicrm.data.model.CustomerModel

@Database(entities = [CustomerModel::class], version = 3, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase() {
    abstract fun getCustomers(): CustomerDao
}