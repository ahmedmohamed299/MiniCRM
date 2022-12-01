package com.ahmed.minicrm.presentation.ui.customers

import androidx.lifecycle.*
import com.ahmed.minicrm.R
import com.ahmed.minicrm.data.model.CustomerModel
import com.ahmed.minicrm.domain.useCases.*
import com.ahmed.minicrm.presentation.ui.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomersViewModel @Inject constructor(
    private val getAllCustomersFromDataBaseUseCase: GetAllCustomersFromDataBaseUseCase,
    private val insertCustomerToDataBaseUseCase: InsertCustomerToDataBaseUseCase,
    private val updateCustomerFromDataBaseUseCase: UpdateCustomerFromDataBaseUseCase,
    private val deleteAllCustomersFromDataBaseUseCase: DeleteAllCustomersFromDataBaseUseCase,
    private val deleteByIdCustomerFromDataBaseUseCase: DeleteByIdCustomerFromDataBaseUseCase,
    private val getCustomerByIdFromDbUseCase:GetCustomerByIdFromDbUseCase
) : ViewModel() {
    private val _snackBarText = MutableLiveData<Event<Int>>()
    val snackBarText: LiveData<Event<Int>> = _snackBarText
    val nameMutableLiveData = MutableLiveData<String?>()
    val numberMutableLiveData = MutableLiveData<String?>()
    val descriptionMutableLiveData = MutableLiveData<String?>()
    private val _taskUpdatedEvent = MutableLiveData<Event<Unit>>()
    val taskUpdatedEvent: LiveData<Event<Unit>> = _taskUpdatedEvent
    fun getAllCustomers() = liveData {
        getAllCustomersFromDataBaseUseCase.execute().collect {
            emit(it)
        }
    }

    fun start(id:Int){
        if (id!=0){
            getCustomerById(id)
        }else{
            nameMutableLiveData.value = null
            numberMutableLiveData.value = null
            descriptionMutableLiveData.value =null
        }
    }

    private fun getCustomerById(id: Int) {

        viewModelScope.launch {
           val customer= getCustomerByIdFromDbUseCase.execute(id)
            customer.let {
                nameMutableLiveData.value=it.name
                numberMutableLiveData.value=it.number
                descriptionMutableLiveData.value=it.description

            }

        }


    }

    fun insertCustomer() = viewModelScope.launch {

        val name = nameMutableLiveData.value
        val number = numberMutableLiveData.value
        val description = descriptionMutableLiveData.value

        if (name == null
            || number == null
            || description == null
        ) {

            _snackBarText.postValue(Event(R.string.empty_task_message))
        } else {
            val customer = CustomerModel(
                name = nameMutableLiveData.value!!,
                number = numberMutableLiveData.value!!,
                description = descriptionMutableLiveData.value!!
            )
            insertCustomerToDataBaseUseCase.execute(customer)
            _taskUpdatedEvent.value = Event(Unit)

        }


    }

    fun updateCustomer(id:Int) = viewModelScope.launch {
        val name = nameMutableLiveData.value
        val number = numberMutableLiveData.value
        val description = descriptionMutableLiveData.value

        if (name == null
            || number == null
            || description == null
        ) {
            _snackBarText.postValue(Event(R.string.empty_task_message))

        }else{
            val customer = CustomerModel(
                id=id,
                name = name,
                number =number,
                description = description
            )
           val x = updateCustomerFromDataBaseUseCase.execute(customer)
            if (x ==1 ){
                _taskUpdatedEvent.value = Event(Unit)

            }
        }

    }

    fun deleteCustomerById(id: Int) = viewModelScope.launch {
        deleteByIdCustomerFromDataBaseUseCase.execute(id)
        _taskUpdatedEvent.value = Event(Unit)
    }

    fun deleteAllCustomers() = viewModelScope.launch {
        deleteAllCustomersFromDataBaseUseCase.execute()
    }




}