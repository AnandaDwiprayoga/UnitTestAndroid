package com.pasukanlangit.id.unittesttdd.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse
import com.pasukanlangit.id.unittesttdd.utils.Resource

class FakeDogRepository : DogRepository {
    private val dogItems = mutableListOf<DogResponse>()

    private val observableDogItems = MutableLiveData<List<DogResponse>>(dogItems)
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableDogItems.postValue(dogItems)
    }

    override suspend fun insertDogItem(dogResponse: DogResponse) {
        dogItems.add(dogResponse)
        refreshLiveData()
    }

    override suspend fun deleteDogItem(dogResponse: DogResponse) {
        dogItems.remove(dogResponse)
        refreshLiveData()
    }

    override fun observableDogItems(): LiveData<List<DogResponse>> = observableDogItems

    override suspend fun getDogItem(): Resource<DogResponse> {
        return if(shouldReturnNetworkError){
            Resource.error("Error occured")
        }else{
            Resource.success(DogResponse(data =  emptyList(), status = ""))
        }
    }
}