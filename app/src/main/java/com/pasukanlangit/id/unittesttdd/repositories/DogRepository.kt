package com.pasukanlangit.id.unittesttdd.repositories

import androidx.lifecycle.LiveData
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse
import com.pasukanlangit.id.unittesttdd.utils.Resource

interface DogRepository {
    suspend fun insertDogItem(dogResponse: DogResponse)
    suspend fun deleteDogItem(dogResponse: DogResponse)
    fun observableDogItems() : LiveData<List<DogResponse>>
    suspend fun getDogItem(): Resource<DogResponse>
}