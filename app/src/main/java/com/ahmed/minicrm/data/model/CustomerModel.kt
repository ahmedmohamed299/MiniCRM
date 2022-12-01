package com.ahmed.minicrm.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CustomerModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? =null,
    var name: String,
    var number: String,
    var description: String
)