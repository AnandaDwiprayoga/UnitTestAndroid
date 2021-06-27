package com.pasukanlangit.id.unittesttdd.data.remote

import com.pasukanlangit.id.unittesttdd.data.remote.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET


interface DogApi {

    @GET
    suspend fun getRandomImage() : Response<DogResponse>
}