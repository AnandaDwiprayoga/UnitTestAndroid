package com.pasukanlangit.id.unittesttdd.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse
import com.pasukanlangit.id.unittesttdd.repositories.DogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.viewModelScope
import com.pasukanlangit.id.unittesttdd.utils.Resource
import com.pasukanlangit.id.unittesttdd.utils.SingleEvent
import com.pasukanlangit.id.unittesttdd.utils.Status
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val repository: DogRepository
): ViewModel(){
    val dogItems = repository.observableDogItems()

    private val _insertDogStatus = MutableLiveData<SingleEvent<Resource<DogResponse>>>()
    val insertDogStatus : LiveData<SingleEvent<Resource<DogResponse>>> = _insertDogStatus

    fun insertDogItem(dogResponse: DogResponse) = viewModelScope.launch {
        with(dogResponse){
            if(data.isEmpty() || status.isEmpty()){
                _insertDogStatus.postValue(SingleEvent(Resource.error("empty value not allowed")))
                return@launch
            }
            repository.insertDogItem(dogResponse)
            _insertDogStatus.postValue(SingleEvent(Resource.success(dogResponse)))
        }
    }

    fun deleteDogItem(dogResponse: DogResponse) = viewModelScope.launch {
        repository.deleteDogItem(dogResponse)
    }

    fun getDogItem() = viewModelScope.launch {
        repository.getDogItem()
    }
}