package com.ahmed.minicrm.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.ahmed.minicrm.data.model.CustomerModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Insert(onConflict = REPLACE)
    suspend fun insertCustomer(customerModel: CustomerModel)

    @Query("SELECT * FROM customerModel")
    fun getAllCustomers(): Flow<List<CustomerModel>>

    @Query("SELECT * FROM customerModel WHERE id = :id")
    suspend fun getCustomerById(id: Int): CustomerModel

    @Update
    suspend fun updateCustomer(customerModel: CustomerModel): Int

    @Query("DELETE FROM customerModel")
    suspend fun deleteCustomer(): Int

    @Query("DELETE FROM customerModel WHERE id = :id")
    suspend fun deleteByName(id: Int): Int
}