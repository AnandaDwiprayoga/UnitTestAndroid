package com.pasukanlangit.id.unittesttdd.repositories

import com.pasukanlangit.id.unittesttdd.data.local.DogDao
import com.pasukanlangit.id.unittesttdd.data.remote.DogApi
import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse
import com.pasukanlangit.id.unittesttdd.utils.Resource
import javax.inject.Inject

class DogRepositoryImpl @Inject constructor(
    private val dogDao: DogDao,
    private val dogApi: DogApi
) : DogRepository {

    override suspend fun insertDogItem(dogResponse: DogResponse) {
        dogDao.insertDog(dogResponse)
    }

    override suspend fun deleteDogItem(dogResponse: DogResponse) {
        dogDao.deleteDog(dogResponse)
    }

    override suspend fun getDogItem(): Resource<DogResponse> {
        return try {
            Resource.loading(null)
            val response = dogApi.getRandomImage()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("error occured", null)
            }else{
               Resource.error("error occured", null)
            }
        }catch (e: Exception){
            Resource.error(e.localizedMessage ?: "Error occured", null)
        }
    }
}